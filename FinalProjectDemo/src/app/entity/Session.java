package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="session_table")
public class Session {

	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sessionId;
	
	@Column
	@NotNull
	private Long userId;
	
	@Column
	@NotNull
	private String sessionKey;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", userId=" + userId + ", sessionKey=" + sessionKey + "]";
	}
	
	
}
