package main;

import java.util.HashMap;

import utils.StringUtils;

public class Problem2 extends Problem {

	public HashMap<String, Object> entry = new HashMap<String, Object>();

	@Override
	boolean check1(String submitted) {
		if (submitted == null)
			return false;
		String[] answer = (String[]) entry.get(Header.answer.name());
		for (String s : answer) {
			if (StringUtils.equalIgnoreCase(submitted, s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	boolean check2(String submitted) {
		// TODO Auto-generated method stub
		return false;
	}
}
