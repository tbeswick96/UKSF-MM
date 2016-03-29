/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core;

import java.io.File;

/**
 * @author Tim
 */
public class Mission {

	private String name, path;
	private File filePath;

	public Mission(String name, String path) {
		this.name = name;
		this.path = path;
		filePath = new File(path);
	}

	public String getName() {return name;}

	@Override
	public String toString() {
		return name;
	}
}
