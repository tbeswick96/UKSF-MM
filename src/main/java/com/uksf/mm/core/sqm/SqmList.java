/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 */
public class SqmList extends ArrayList<String>  {

	/**
	 * Creates new empty SqmList
	 */
	public SqmList() {
		super();
	}

	/**
	 * Creats new SqmList with given strings list
	 * @param strings list of strings to initialise SqmList with
	 */
	public SqmList(List<String> strings) {
		super(strings);
	}

	/**
	 * Checks if the list contains the given object, case insensitive
	 * @param object string to check
	 * @return true if list contains string
	 */
	@Override public boolean contains(Object object) {
		String check = (String) object;
		for(String line : this){
			if(line.toLowerCase().contains(check)) return true;
		}
		return false;
	}
}
