package com.xabaohui.modules.product.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static Timestamp getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(date);
		return Timestamp.valueOf(str);
	}
}
