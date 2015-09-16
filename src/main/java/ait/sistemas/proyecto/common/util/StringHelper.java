package ait.sistemas.proyecto.common.util;

public class StringHelper {
	
	public static <T> String implode(Iterable<T> elements, String separator) {
		StringBuilder str_builder = new StringBuilder();
		boolean first = true;
		for (Object s : elements) {
			if (s == null)
				continue;
			if (first)
				first = false;
			else
				str_builder.append(separator);
			str_builder.append(s);
		}
		return str_builder.toString();
	}
	
	public static String implode(int[] columnssizes, String separator) {
		StringBuilder str_builder = new StringBuilder();
		for (int i = 0; i < columnssizes.length; i++) {
			if (String.valueOf(columnssizes[i]) == null)
				continue;
			if (i == 0) {
			} else {
				str_builder.append(separator);
				str_builder.append(String.valueOf(columnssizes[i]));
			}
		}
		return null;
	}

	public static String implode(String[] data, String separator) {
		StringBuilder str_builder = new StringBuilder();
		boolean first = true;
		for (Object s : data) {
			if (s == null)
				continue;
			if (first)
				first = false;
			else
				str_builder.append(separator);
			str_builder.append(s);
		}
		return str_builder.toString();
	}
}
