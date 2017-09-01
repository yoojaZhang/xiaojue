package com.eg.mp.web.error;

/**
 * 操作状态
 *
 * @author geliangjian
 */
public class OperationStatus {

    public static final boolean SUCCESS = true;
    public static final boolean FAIL = false;

    private Boolean status;
    private String title;
    private String text;

    public OperationStatus(Boolean status) {
        super();
        this.status = status;
    }

    /**
     * 操作成功
     *
     * @return
     */
    public static OperationStatus success() {
        return new OperationStatus(SUCCESS);
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static OperationStatus failed() {
        return new OperationStatus(FAIL);
    }

    /**
     * 提示信息
     *
     * @param title 提示信息标题
     * @return
     */
    public OperationStatus message(String title) {
        this.title = title;
        return this;
    }

    /**
     * 提示信息
     *
     * @param title 提示信息标题
     * @param text  提示信息正文
     * @return
     */
    public OperationStatus message(String title, String text) {
        this.title = title;
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"success\":");
        sb.append(this.status);
        sb.append(",\"title\":\"");
        if (this.title != null && !"".equalsIgnoreCase(this.title)) {
            sb.append(this.title);
        }
        sb.append("\",\"text\":\"");
        if (this.text != null && !"".equalsIgnoreCase(this.text)) {
            sb.append(this.text);
        }
        sb.append("\"}");
        return sb.toString();
    }

}
