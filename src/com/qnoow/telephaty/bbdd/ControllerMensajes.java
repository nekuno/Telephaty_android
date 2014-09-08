package com.qnoow.telephaty.bbdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ControllerMensajes {
	SQLiteDatabase db;
	BBDDMensajes mensajesDB;
	String[] campos = new String[] { "id", "mac" };

	public ControllerMensajes(Context context) {
		mensajesDB = new BBDDMensajes(context, "Mensajes", null, 1);
	}

	public boolean insert(String id, String mac) {
		db = mensajesDB.getWritableDatabase();
		if (db != null && !search(id, mac)) {
			db.execSQL("INSERT INTO Mensajes (id, mac) " + "VALUES ('" + id
					+ "', '" + mac + "')");
			db.close();
			return true;
		}
		return false;

	}


	private boolean search(String id, String mac) {

		Cursor c = db.query("Mensajes", campos, null, null, null, null, null);

		if (c.moveToFirst()) {
			do {
				if (c.getString(0).equals(id) && c.getString(1).equals(mac))
					return true;
			} while (c.moveToNext());
		}
		return false;
	}

}
