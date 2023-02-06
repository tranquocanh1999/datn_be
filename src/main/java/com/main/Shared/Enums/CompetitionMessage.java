package com.main.Shared.Enums;

public enum CompetitionMessage {

    CODE_NOT_NULL("Mã bài thi không được để trống."),
    CODE_MAX_LENGTH("Mã bài thi không quá 100 kí tự."),
    TITLE_NOT_NULL("Tiêu đề không được để trống."),
    TITLE_MAX_LENGTH("Tiêu đề nhập không quá 255 kí tự."),
    EXAM_NUMBER_MIN("Số lượng bài thi phải lớn hơn 0."),
    EXAM_NUMBER_MAX("Số lượng bài thi không quá 100."),
    QUESTION_NUMBER_MIN("Số lượng câu hỏi phải lớn hơn 0."),
    QUESTION_NUMBER_MAX("Số lượng câu hỏi không quá 100."),
    COMPETITION_NOT_EXIST("Bài thi không tồn tại hoặc đã bị xóa."),

    DELETE_SUCCESS("Xóa bài thi thành công."),
    UPDATE_SUCCESS("Chỉnh sửa bài thi thành công."),
    CREATE_SUCCESS("Tạo mới bài thi thành công.");

    private final String value;

    private CompetitionMessage(String type) {
        value = type;
    }

    public String value() {
        return this.value;
    }
}
