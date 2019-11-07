package com.ha.pojos;

import java.util.Map;

public class Task extends TaskKey {
    private String subject;

    private String content;

    private String remarks;

    private String time;
    
    private Map state;

    public Map getState() {
		return state;
	}

	public void setState(Map state) {
		this.state = state;
	}

	public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}