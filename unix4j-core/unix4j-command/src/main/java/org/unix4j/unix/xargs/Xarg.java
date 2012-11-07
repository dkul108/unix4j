package org.unix4j.unix.xargs;

import org.unix4j.convert.StringConverters;
import org.unix4j.convert.ValueConverter;
import org.unix4j.vars.TypedStringVar;
import org.unix4j.vars.TypedStringsVar;

public class Xarg {
	public static final TypedStringVar $0 = argVar(0);
	public static final TypedStringVar $1 = argVar(1);
	public static final TypedStringVar $2 = argVar(2);
	public static final TypedStringVar $3 = argVar(3);
	public static final TypedStringVar $4 = argVar(4);
	public static final TypedStringVar $5 = argVar(5);
	public static final TypedStringVar $6 = argVar(6);
	public static final TypedStringVar $7 = argVar(7);
	public static final TypedStringVar $8 = argVar(8);
	public static final TypedStringVar $9 = argVar(9);
	public static final TypedStringVar $string = new TypedStringVar(args(), StringConverters.DEFAULT);
	public static final TypedStringsVar $strings = new TypedStringsVar(args(), new ValueConverter<String[]>() {
		@Override
		public String[] convert(Object value) {
			if (value != null) {
				return value.toString().split(" ");
			}
			return null;
		}
	});
	
	
	public static final String args() {
		return "$*";
	}
	public static final String arg(int index) {
		return "$" + index;
	}
	public static final TypedStringVar argVar(int index) {
		return new TypedStringVar(arg(9), StringConverters.DEFAULT);
	}
}
