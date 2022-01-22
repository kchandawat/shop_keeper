package com.marquedo.marquedo;

import static com.marquedo.marquedo.InternetCheck.getConnectionType;
import static com.marquedo.marquedo.InternetCheck.getConnectivityStatusString;
import static com.marquedo.marquedo.OurConstants.OTP_FCMID_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PHONE_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;
import static com.marquedo.marquedo.OurConstants.OTP_SHOPCATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_SHOPNAME_KEY;
import static com.marquedo.marquedo.OurConstants.TYPE_NOT_CONNECTED;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.installations.FirebaseInstallations;
import com.marquedo.marquedo.Authentication.Mobile_Login;
import com.marquedo.marquedo.BusinessDetails.Business_Detail;
import com.marquedo.marquedo.Home.progress;
import com.mukesh.OtpView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
    private static final String TAG = "OTPActivity";
    private static final int SHOW_TIME = 7000;
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";
    private static final long RESEND_WAIT_MILLIS = 45000;
    private static final String EXTRA_MILLIS_UNTIL_FINISHED = "EXTRA_MILLIS_UNTIL_FINISHED";

    // variable for FirebaseAuth class
    private FirebaseAuth firebaseAuth;

    // string for storing our verification ID
    private String mVerificationId;
    private OtpView otpView;
    private MaterialButton sendOtp;
    private String phone;
    private boolean mVerificationInProgress = false;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    private CustomCountDownTimer mCountdownTimer;
    private long mMillisUntilFinished;
    private FirebaseFirestore db;
    private int flag;
    private int flagOP = 0;
    private SharedPreferences sharedPreferences;
    private TextView mCountDownTextView;
    private ImageButton otpBackButton;
    private ProgressBar progressBar;
    private ObjectAnimator animation;
    private InternetCheck internetCheck;
    private Snack snack;

    private final View.OnClickListener allClickListenerHandlingCL = this::allClickListenerHandling;

    private void allClickListenerHandling(View view) {
        if (view.getId() == R.id.continue_button){
            int conn = getConnectionType(this);
            if (conn == TYPE_NOT_CONNECTED) {
                snack.snackBar(sendOtp, "Please connect to internet to continue!");
                return;
            }
            if (Objects.requireNonNull(otpView.getText()).length()==0) {
                otpView.setError("Please enter otp");
                snack.snackBar(sendOtp, "Please enter otp");
            } else if (otpView.getText().length()<6) {
                otpView.setError("Please enter the 6-digit code");
                snack.snackBar(sendOtp, "Please enter the 6-digit code");
            } else if (otpView.getText().length()==6)
                submitConfirmationCode();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_otppage);
        initializations();
        operations();
        buttononclicksthis();
    }

    private void initializations() {
        // below line is for getting instance
        // of our FirebaseAuth.
        firebaseAuth = FirebaseAuth.getInstance();
        // Access a Cloud Firestore instance from your Activity

        db = FirebaseFirestore.getInstance();
        otpView = findViewById(R.id.otp_view);
        TextView sentToNumberTextView = findViewById(R.id.sent_to_number_textView);
        sharedPreferences = getSharedPreferences(OTP_PREFERENCES, Context.MODE_PRIVATE);
        mCountDownTextView =findViewById(R.id.count_down_timer);
        phone = "+91" + getIntent().getStringExtra(OurConstants.PHONE_NUMBER_LOGIN);
        String toPhone = "We sent it to " + phone;
        sentToNumberTextView.setText(toPhone);
        sendOtp = findViewById(R.id.continue_button);
        otpBackButton = findViewById(R.id.otp_back_button);
        progressBar = findViewById(R.id.progressBar);
        animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 500);
        animation.setInterpolator(new DecelerateInterpolator());
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String status = getConnectivityStatusString(context);
                internetCheck.setSnackbarMessage(status, sendOtp);
            }
        };
        internetCheck = new InternetCheck(true, broadcastReceiver, this);
        snack = new Snack(this);
    }

    private void test(){
        String phoneNumber = "+16505554567";
        String smsCode = "123456";

        FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

// Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode);
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(@NonNull final PhoneAuthCredential credential) {
                        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(otp.this, task -> {
                            if (task.isSuccessful()) {
                                otpView.setText(smsCode);
                                Log.d(TAG, "verificationCompleted");
                                progressBar.setVisibility(View.VISIBLE);
                                animation.start();
                                sendOtp.setEnabled(false);
                                fullSignIn();
                                //verifyCode(mVerificationId, smsCode);
                            } else {
                                if (flagOP < 3) {
                                    Log.d(TAG, "operations" + flagOP);
                                    operations();
                                    flagOP++;
                                    snack.snackBar(sendOtp, "Something Went Wrong! Please Wait While We Try Again...");
                                } else {
                                    snack.snackBar(sendOtp, "Something Went Wrong! Please Try Again With Phone Number Verification");
                                    if (firebaseAuth.getCurrentUser() != null)
                                        firebaseAuth.signOut();
                                    Log.d(TAG, "userSignedOut");
                                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                        Intent intent = new Intent(otp.this, Mobile_Login.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }, SHOW_TIME);
                                }
                            }
                        });
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        mVerificationInProgress = false;

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            snack.snackBar(sendOtp, "Invalid phone number.");
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            snack.snackBar(sendOtp, "Quota exceeded.");
                        }
                        Log.d(TAG, "verificationFailed");
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        mVerificationId = verificationId;
                        mResendToken = token;
                        Log.d(TAG, "onCodeSent");
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        setupCountDown();
        mVerificationInProgress = true;
        Log.d(TAG, "test");
    }

    private void operations() {
        otpBackButton.setOnClickListener(view -> backPressed());
        Log.d(TAG, "operations");
        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull final PhoneAuthCredential credential) {
                firebaseAuth.signInWithCredential(credential).addOnCompleteListener(otp.this, task -> {
                    if (task.isSuccessful()) {
                        String code = credential.getSmsCode();
                        otpView.setText(code);
                        Log.d(TAG, "verificationCompleted");
                        progressBar.setVisibility(View.VISIBLE);
                        animation.start();
                        sendOtp.setEnabled(false);
                        fullSignIn();
                        //verifyCode(mVerificationId, code);
                    } else {
                        if (flagOP < 3) {
                            Log.d(TAG, "operations" + flagOP);
                            operations();
                            flagOP++;
                            snack.snackBar(sendOtp, "Something Went Wrong! Please Wait While We Try Again...");
                        } else {
                            snack.snackBar(sendOtp, "Something Went Wrong! Please Try Again With Phone Number Verification");
                            if (firebaseAuth.getCurrentUser() != null)
                                firebaseAuth.signOut();
                            Log.d(TAG, "userSignedOut");
                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                Intent intent = new Intent(otp.this, Mobile_Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }, SHOW_TIME);
                        }
                    }
                });
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                mVerificationInProgress = false;

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    snack.snackBar(sendOtp, "Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    snack.snackBar(sendOtp, "Quota exceeded.");
                }
                Log.d(TAG, "verificationFailed");
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                Log.d(TAG, "onCodeSent");
            }
        };
        test();
        //sendCode(phone);
    }

    private void buttononclicksthis() {
        sendOtp.setOnClickListener(allClickListenerHandlingCL);
    }

    private void sendCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        setupCountDown();
        mVerificationInProgress = true;
    }

    // below method is use to verify code from Firebase.
    private void verifyCode(String verificationId, String code) {
        Log.d(TAG, "verifyCode");

        // below line is used for getting getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        try {
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
            snack.snackBar(sendOtp, Objects.requireNonNull(e.getMessage()));
            Log.d(TAG, e.getMessage());
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");

                        // Update UI
                        // if the code is correct and the task is successful
                        // we are sending our user to new activity.
                        fullSignIn();
                    } else
                        // Sign in failed, display a message and update the UI
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            snack.snackBar(sendOtp, Objects.requireNonNull(Objects.requireNonNull(task.getException()).getMessage()));
                        }
                        sendOtp.setEnabled(true);
                    });
    }

    private void fullSignIn() {
        Log.d(TAG, "fullSignIn");
        FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "taskSuccessful");
                final Map<String, Object> shopOwner = new HashMap<>();
                if (firebaseAuth.getCurrentUser() != null) {
                    Log.d(TAG, "userIS");
                    final String uid = firebaseAuth.getCurrentUser().getUid();
                    final String fcm = Objects.requireNonNull(task.getResult()).getToken();
                    shopOwner.put(OTP_PHONE_KEY, phone);
                    shopOwner.put(OTP_FCMID_KEY, fcm);
                    final Map<String, Object> exists = new HashMap<>();
                    exists.put(OTP_FCMID_KEY, fcm);
                    final DocumentReference check = db.collection("Store").document(uid);
                    check.get().addOnCompleteListener(task12 -> {
                        if (task12.isSuccessful()) {
                            Log.d(TAG, "storeTaskSuccessful");
                            progressBar.clearAnimation();
                            progressBar.setVisibility(View.INVISIBLE);
                            DocumentSnapshot doc = task12.getResult();
                            String number = doc.getString(OTP_PHONE_KEY);
                            if (number == null) {
                                check.set(shopOwner)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d(TAG, "taskSuccessfulBusinessDetail");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(OTP_PHONE_KEY, phone);
                                        editor.putString(OTP_FCMID_KEY, fcm);
                                        editor.apply();
                                        final String shopName = doc.getString(OTP_SHOPNAME_KEY);
                                        if (shopName == null) {
                                            Log.d(TAG, "BusinessDetail");
                                            startActivity(new Intent(this, Business_Detail.class)
                                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                        }
                                    })
                                    .addOnFailureListener(e -> {
                                        progressBar.clearAnimation();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        sendOtp.setEnabled(true);
                                        snack.snackBar(sendOtp, "ERROR" + e.getMessage());
                                    });
                            } else {
                                check.set(shopOwner)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d(TAG, "taskSuccessfulBusinessDetail");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(OTP_PHONE_KEY, phone);
                                        editor.putString(OTP_FCMID_KEY, fcm);
                                        editor.apply();
                                        final String shopName = doc.getString(OTP_SHOPNAME_KEY);
                                        if (shopName == null) {
                                            Log.d(TAG, "BusinessDetail");
                                            startActivity(new Intent(this, Business_Detail.class)
                                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                        } else {
                                            check.update(exists).addOnCompleteListener(task1 -> {
                                                if (task1.isSuccessful()) {
                                                    Log.d(TAG, "existTaskSuccessful");
                                                    editor.putString(OTP_PHONE_KEY, phone);
                                                    editor.putString(OTP_SHOPNAME_KEY, shopName);
                                                    String shopCategory = doc.getString(OTP_SHOPCATEGORY_KEY);
                                                    editor.putString(OTP_SHOPCATEGORY_KEY, shopCategory);
                                                    editor.apply();
                                                    startActivity(new Intent(this, progress.class)
                                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                                    //getDetails(uid);
                                                } else {
                                                    if (flag <= 2) {
                                                        Log.d(TAG, "fullSignIn" + flag);
                                                        fullSignIn();
                                                        flag++;
                                                        snack.snackBar(sendOtp, "Something Went Wrong! Please Wait While We Try Again...");
                                                    } else {
                                                        snack.snackBar(sendOtp, "Something Went Wrong! Please Try Again With Phone Number Verification");
                                                        if (firebaseAuth.getCurrentUser() != null)
                                                            firebaseAuth.signOut();
                                                        Log.d(TAG, "userSignedOutFullSignIn");
                                                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                                            Intent intent = new Intent(this, Mobile_Login.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                            startActivity(intent);
                                                        }, SHOW_TIME);
                                                    }
                                                }
                                            })
                                                    .addOnFailureListener(e -> {
                                                        progressBar.clearAnimation();
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        snack.snackBar(sendOtp, "ERROR" + e.getMessage());
                                                    });
                                        }
                                    })
                                    .addOnFailureListener(e -> {
                                        progressBar.clearAnimation();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        sendOtp.setEnabled(true);
                                        snack.snackBar(sendOtp, "ERROR" + e.getMessage());
                                    });
                            }
                        } else {
                            progressBar.clearAnimation();
                            progressBar.setVisibility(View.INVISIBLE);
                            check.set(shopOwner)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d(TAG, "taskSuccessfulBusinessDetail");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(OTP_PHONE_KEY, phone);
                                        editor.putString(OTP_FCMID_KEY, fcm);
                                        editor.apply();
                                        Intent intent = new Intent(this, Business_Detail.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    })
                                    .addOnFailureListener(e -> {
                                        progressBar.clearAnimation();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        sendOtp.setEnabled(true);
                                        snack.snackBar(sendOtp, "ERROR" + e.getMessage());
                                    });
                        }
                    });
                } else {
                    progressBar.clearAnimation();
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d(TAG, "Could not confirm authenticity");
                    snack.snackBar(sendOtp, "Could not confirm authenticity. Please try again!");
                }
            } else {
                snack.snackBar(sendOtp, "Something Went Wrong! Please Try Again With Phone Number Verification");
                if (firebaseAuth.getCurrentUser() != null)
                    firebaseAuth.signOut();
                Log.d(TAG, "taskUnsuccessfulUserSignedOut");
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    progressBar.clearAnimation();
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(this, Mobile_Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }, SHOW_TIME);
            }
        });
    }

    private void setTimer(long millisUntilFinished) {
        mCountDownTextView.setText(String.format(getString(R.string.fui_resend_code_in),
                timeRoundedToSeconds(millisUntilFinished)));
    }

    private void setupCountDown() {
        //sendOtp.setEnabled(false);
        //set the timer view
        setTimer(RESEND_WAIT_MILLIS / 1000);

        //create a countdown
        mCountdownTimer = createCountDownTimer(mCountDownTextView, /*mResendButton,*/ this);

        Log.d(TAG, "setUpCountDown");
        //start the countdown
        startTimer();
    }

    private void submitConfirmationCode() {
        int conn = getConnectionType(this);
        if (conn == TYPE_NOT_CONNECTED) {
            Toast.makeText(this, "Please connect to internet to continue!", Toast.LENGTH_LONG).show();
            return;
        }
        sendOtp.setEnabled(false);
        Log.d(TAG, "submitConfirmationCode");
        progressBar.setVisibility(View.VISIBLE);
        animation.start();
        verifyCode(mVerificationId, Objects.requireNonNull(otpView.getText()).toString());
    }

    private CustomCountDownTimer createCountDownTimer(final TextView timerText, final otp fragment) {
        return new CustomCountDownTimer(RESEND_WAIT_MILLIS, 500) {
            final otp mSubmitConfirmationCodeFragment = fragment;

            public void onTick(long millisUntilFinished) {
                mMillisUntilFinished = millisUntilFinished;
                mSubmitConfirmationCodeFragment.setTimer(millisUntilFinished);
            }

            public void onFinish() {
                timerText.setEnabled(true);
                timerText.setText(getResources().getString(R.string.resend_otp));
                timerText.setOnClickListener(view -> resendVerificationCode(phone, mResendToken));
            }
        };
    }

    private void startTimer() {
        if (mCountdownTimer != null) {
            mCountdownTimer.start();
        }
    }

    private void cancelTimer() {
        if (mCountdownTimer != null) {
            mCountdownTimer.cancel();
        }
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        int conn = getConnectionType(this);
        if (conn == TYPE_NOT_CONNECTED) {
            snack.snackBar(sendOtp, "Please connect to internet to continue!");
            return;
        }
        mCountDownTextView.setEnabled(false);
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        setupCountDown();
    }

    private int timeRoundedToSeconds(double millis) {
        return (int) Math.ceil(millis / 1000);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mVerificationInProgress) {
            test();
            //sendCode(phone);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
        outState.putLong(EXTRA_MILLIS_UNTIL_FINISHED, mMillisUntilFinished);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
        if (!mVerificationInProgress) {
            Toast.makeText(this, "Registration timed out! Please try again!", Toast.LENGTH_LONG).show();
        }
        mCountdownTimer.update(savedInstanceState.getLong(EXTRA_MILLIS_UNTIL_FINISHED));
    }

    @Override
    protected void onResume() {
        super.onResume();
        internetCheck.registerInternetCheckReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(internetCheck.broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
        mVerificationInProgress = false;
    }

    @Override
    public void onBackPressed() {
        backPressed();
    }

    private void backPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Please check!")
                .setMessage("Do you want to abort the process of registration?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    mVerificationInProgress = false;
                    otp.super.onBackPressed();
                })
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .create()
                .show();
    }
}