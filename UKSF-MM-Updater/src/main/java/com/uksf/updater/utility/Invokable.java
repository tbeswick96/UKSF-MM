/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.utility;

import com.uksf.updater.core.Core;

/**
 * @author Tim
 */
@SuppressWarnings("unused")
public class Invokable {

    /**
     * Invokable instance
     */
    public static Invokable instance = new Invokable();

	/**
	 * Closes program
	 */
	public void close() {
		LogHandler.log("Updater closing");
		System.exit(0);
	}

	/**
	 * Minimizes program
	 */
	public void minimize() {
		LogHandler.log("Updater minimizing");
		Core.getInstanceUI().minimize();
	}

	/**
	 * Maximizes program
	 */
	public void maximize() {
		LogHandler.log("Updater maximizing");
		Core.getInstanceUI().maximize();
	}
}
