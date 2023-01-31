package com.main.Shared.Enums;

public enum CommonMessage {
    LOGOUT_SUCCESS("Đăng xuất thành công."),
    SERVER_ERROR("Hệ thống xảy ra lỗi, vui lòng liên hệ admin để tìm hiểu thêm."),
    NO_TOKEN("Bạn chưa đăng nhập trước đây, vui lòng đăng nhập"),
    LOGIN_FAILED("Tên đăng nhập hoặc mật khẩu không đúng, vui lòng đăng nhập lại."),
    TOKEN_EXPIRED("Token hết hạn, vui lòng đăng nhập lại.");

    private final String value;

    private CommonMessage(String type) {
        value = type;
    }

    public String value() {
        return this.value;
    }
}
