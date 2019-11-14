package com.ha.pojos;

import java.util.Map;

public class Task {
    private Integer tid;

    private Integer cid;

    private String subject;

    private String content;

    private String remarks;

    private String time;
    
    private String title;
    
    private Map statistics;
    
    private byte perState;

    

	public byte getPerState() {
		return perState;
	}

	public void setPerState(byte perState) {
		this.perState = perState;
	}

	public Map getStatistics() {
		return statistics;
	}

	public void setStatistics(Map statistics) {
		this.statistics = statistics;
	}

	public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}