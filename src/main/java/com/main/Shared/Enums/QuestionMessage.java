package com.main.Shared.Enums;

public enum QuestionMessage {

    CONTENT_NOT_NULL("Nội dung câu hỏi không được để trống"),
    ANSWER_NOT_NULL("Đáp án không được để trống"),
    QUESTION_NOT_EXIST("Câu hỏi không tồn tại hoặc đã bị xóa."),
    DELETE_SUCCESS("Xóa câu hỏi thành công."),
    UPDATE_SUCCESS("Chỉnh sửa câu hỏi thành công."),
    CREATE_SUCCESS("Tạo mới câu hỏi thành công.");

    private final String value;

    private QuestionMessage(String type) {
        value = type;
    }

    public String value() {
        return this.value;
    }
}
