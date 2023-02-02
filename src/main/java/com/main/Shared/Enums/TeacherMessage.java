package com.main.Shared.Enums;

public enum TeacherMessage {

    NAME_NOT_NULL("Tên giáo viên không được để trống."),
    NAME_MAX_LENGTH("Tên giáo viên không quá 255 kí tự."),
    USERNAME_NOT_NULL("Tên đăng nhập không được để trống."),
    USERNAME_IS_USED("Tên đăng nhập đã được sử dụng."),
    USERNAME_MAX_LENGTH("Tên đăng nhập không quá 100 kí tự."),
    PHONE_NUMBER_NOT_NULL("Số điện thoại không được để trống."),
    PHONE_NUMBER_MAX_LENGTH("Số điện thoại không quá 10 kí tự."),
    PASSWORD_NOT_NULL("Mật khẩu không được để trống."),
    PASSWORD_MAX_LENGTH("Mật khẩu không quá 255 kí tự."),
    PASSWORD_MIN_LENGTH("Mật khẩu phải nhiều hơn 7 kí tự."),
    EMAIL_NOT_NULL("Email không được để trống."),
    EMAIL_MAX_LENGTH("Email không quá 255 kí tự."),
    EMAIL_NOT_CORRECT("Email không đúng định dạng."),
    STUDENT_NOT_EXIST("Giáo viên không tồn tại hoặc đã bị xóa."),
    DELETE_SUCCESS("Xóa giáo viên thành công."),
    UPDATE_SUCCESS("Chỉnh sửa giáo viên thành công."),
    CREATE_SUCCESS("Tạo mới giáo viên thành công.");

    private final String value;

    private TeacherMessage(String type) {
        value = type;
    }

    public String value() {
        return this.value;
    }
}
