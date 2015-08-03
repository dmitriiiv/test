package by.academy.util;

import java.util.ResourceBundle;

public enum PropertiesManager {
	POOL {
		@Override
		public String getProperty(String key) {
			return ResourceBundle.getBundle("by.academy.dataBase").getString(key);
		}
	},
	SQL_REQUEST {
		@Override
		public String getProperty(String key) {
			return ResourceBundle.getBundle("by.academy.sqlRequests").getString(key);
		}
	},
	MESSAGE {
		@Override
		public String getProperty(String key) {
			return ResourceBundle.getBundle("by.academy.messages").getString(key);
		}
	};
	
	public abstract String getProperty(String key);

}
