
package com.kaniwu.common.bean;

/**
 * 
 * 
 * @author wurb3
 * 
 */
public class MusicOutMessageBean extends OutMessageBean {
	private String	MsgType	= "music";
	private String	MusicUrl;
	private String	HQMusicUrl;

	public String getMsgType() {
		return MsgType;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
}
