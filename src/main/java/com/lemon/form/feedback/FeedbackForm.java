package com.lemon.form.feedback;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by simpletour_Jenkin on 2017/1/25.
 */
public class FeedbackForm {
    // 内容
    @NotBlank
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
