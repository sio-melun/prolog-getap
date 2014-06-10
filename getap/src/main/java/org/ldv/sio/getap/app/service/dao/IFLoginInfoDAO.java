package org.ldv.sio.getap.app.service.dao;

import java.util.List;

import org.ldv.sio.getap.app.LoginInfo;

public interface IFLoginInfoDAO {

	public List<LoginInfo> getLoginInfoById(String id);

}
