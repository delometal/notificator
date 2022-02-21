package com.perigea.tracker.notificator.generic;

import com.perigea.tracker.commons.model.GenericNotification;

public interface NotificaInterface <T extends GenericNotification> {

	public void mandaNotifica(T notifica);
	
}
