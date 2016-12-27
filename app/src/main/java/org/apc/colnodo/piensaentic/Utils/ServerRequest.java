package org.apc.colnodo.piensaentic.Utils;

import android.app.ProgressDialog;

import java.util.List;

/**
 * Created by apple on 11/19/16.
 */

public class ServerRequest {


    private static final String API = "/api/";
    private static final String REGISTER_USER_VIEW = "register-user/";
    private static final String REGISTER_ACTIVITY_FINISHED_VIEW = "activity-register/";
    private static final String PASSWORD_RECOVERY_VIEW = "password-recovery/";

    public static class RegisterUser extends RequestTask{
        public RegisterUser(OnRequestCompleted listener, ProgressDialog loader, int taskId, String name,String nick_name,
                            String email, String birthdate, boolean terms_conditions_accepted){
            super(listener, loader, taskId);
            setUrl(LocalConstants.URL + API + REGISTER_USER_VIEW);

            setRequest(getRegisterUser(email, name, nick_name, birthdate, terms_conditions_accepted));
            setResponse(new RegisterUserResponse());
        }

        private RegisterUserModel getRegisterUser(String email, String name, String nick_name,
                                                  String birthdate, boolean term_cond){
            RegisterUserModel request = new RegisterUserModel();
            request.email = email;
            request.first_name = name;
            request.nick_name = nick_name;
            request.birthdate = birthdate;
            request.terms_conditions_accepted = term_cond;

            return request;
        }
    }

    public static class RegisterActivity extends RequestTask{
        public RegisterActivity(OnRequestCompleted listener, int taskId, ActivityRegisterList list ){
            super(listener, taskId);
            setUrl(LocalConstants.URL + API + REGISTER_ACTIVITY_FINISHED_VIEW);
            setRequest(list);
            setResponse(new RegisterActivityResponse());
        }
    }

    public static class PasswordRecoveryActivity extends RequestTask{
        public PasswordRecoveryActivity(OnRequestCompleted listener, int taskId, String mail, String password ){
            super(listener, taskId);
            setUrl(LocalConstants.URL + API + PASSWORD_RECOVERY_VIEW);
            setRequest(new PasswordRecoveryModel(mail, password));
            setResponse(new PasswordRecoveryResponse());
        }
    }

    public static class RegisterUserModel{
        public String email;
        public String first_name;
        public String nick_name;
        public String birthdate;
        public boolean terms_conditions_accepted;
    }

    public static class ActivityRegisterList{
        public String user_mail;
        public List<ActivityRegister> activities_register;
    }

    public static class ActivityRegister{

        public ActivityRegister(String activity, boolean execution){
            activity_executed = activity;
            execution_state = execution;
        }

        public String activity_executed;
        public boolean execution_state;
    }


    public static class RegisterUserResponse{
        public String username;
        public String created;
    }

    public static class RegisterActivityResponse{
        public String updated;
    }

    public static class PasswordRecoveryResponse{
        public String sent;
    }

    public static class PasswordRecoveryModel{

        public PasswordRecoveryModel(String mail, String password){
            user_mail = mail;
            this.password = password;
        }

        public String user_mail;
        public String password;
    }
}
