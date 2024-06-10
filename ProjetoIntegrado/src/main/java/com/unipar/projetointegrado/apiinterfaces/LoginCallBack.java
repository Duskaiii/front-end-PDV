package com.unipar.projetointegrado.apiinterfaces;

public interface LoginCallBack {
    void onLoginSuccess();
    void onLoginFailure(String message);
}
