package com.main.Shared.Enums;

public enum ClassMessage {

    NAME_NOT_NULL("Tên lớp không được để trống."),
    TEACHER_NOT_NULL("Giáo viên không được để trống."),
    NAME_MAX_LENGTH("Tên lớp không quá 255 kí tự."),
    DESCRIPTION_MAX_LENGTH("Mô tả lớp không quá 255 kí tự."),
    CLASS_NOT_EXIST("Lớp học không tồn tại hoặc đã bị xóa."),
    DELETE_SUCCESS("Lớp học đã được xóa thành công."),
    UPDATE_SUCCESS("Lớp học được update thành công."),
    CREATE_SUCCESS("Lớp học được tạo mới thành công.");

    private final String value;

    private ClassMessage(String type) {
        value = type;
    }

    public String value() {
        return this.value;
    }
}
