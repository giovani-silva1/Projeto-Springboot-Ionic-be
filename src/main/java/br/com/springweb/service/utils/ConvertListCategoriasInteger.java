package br.com.springweb.service.utils;

import java.util.ArrayList;
import java.util.List;

public class ConvertListCategoriasInteger {

	public static List<Integer> convertList(String url) {

		String[] novaString = url.split(",");
		List<Integer> ids = new ArrayList<>();
		if(url == "") {
			return null;
		}
		for (String StringConvertida : novaString) {
			ids.add(Integer.parseInt(StringConvertida));
		}

		return ids;
	}
}
