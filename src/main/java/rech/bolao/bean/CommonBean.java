package rech.bolao.bean;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class CommonBean {

	public abstract StringBuffer toJson();

	public abstract StringBuffer toInsertString();

	protected StringBuffer toJson(Class<? extends CommonBean> clazz, StringBuffer ret) {
		if (ret == null) {
			ret = new StringBuffer();
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		int fieldCounter = 0;
		for (Field field : declaredFields) {
			fieldCounter++;
			try {
				if (fieldCounter == 1) {
					ret.append("{");
				} else {
					ret.append(", ");
				}
				ret.append("'").append(field.getName()).append("' : ");
				if (field.getType().getName().toLowerCase().startsWith("int")) {
					ret.append("'").append(field.getInt(this)).append("'");
				}
				if (field.getType().getName().toLowerCase().contains("string")) {
					ret.append("'").append((String) field.get(this)).append("'");
				}
				if (field.getType().getName().toLowerCase().contains("calendar")) {
					Object c = field.get(this);
					if (c == null) {
						ret.append("null");
					} else {
						Calendar c1 = (Calendar) c;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						ret.append("'").append(sdf.format(c1.getTime())).append("'");
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.err.println(field.getName());
				e.printStackTrace();
			}
		}
		ret.append(" }");
		return ret;
	}

	protected StringBuffer toInsertString(Class<? extends CommonBean> clazz, String prefix) {
		StringBuffer ret = new StringBuffer(prefix);
		Field[] declaredFields = clazz.getDeclaredFields();
		int fieldCounter = 0;
		for (Field field : declaredFields) {
			if (field.getType().getName().toLowerCase().contains("arraylist") || field.getType().getName().toLowerCase().contains("bean")) {
				continue;
			}
			fieldCounter++;
			try {
				if (fieldCounter > 1) {
					ret.append(", ");
				}
				if (field.getType().getName().toLowerCase().startsWith("int")) {
					ret.append(field.getInt(this));
				}
				if (field.getType().getName().toLowerCase().contains("string")) {
					ret.append("'").append((String) field.get(this)).append("'");
				}
				if (field.getType().getName().toLowerCase().contains("calendar")) {
					Object c = field.get(this);
					if (c == null) {
						ret.append("null");
					} else {
						Calendar c1 = (Calendar) c;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						ret.append("'").append(sdf.format(c1.getTime())).append("'");
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.err.println(field.getName());
				e.printStackTrace();
			}
		}
		ret.append(");");
		return ret;
	}
}
