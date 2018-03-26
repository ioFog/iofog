/*******************************************************************************
 * Copyright (c) 2016, 2017 Iotracks, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Saeid Baghbidi
 * Kilton Hopkins
 *  Ashita Nagar
 *******************************************************************************/
package org.eclipse.iofog.process_manager;

import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.async.ResultCallbackTemplate;

import java.util.concurrent.CountDownLatch;

/**
 * Docker command result callback
 * 
 * @author saeid
 *
 */
public class StatsCallback extends ResultCallbackTemplate<StatsCallback, Statistics> {
    private Statistics stats = null;
	private CountDownLatch countDownLatch;

	public StatsCallback(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

    public Statistics getStats() {
		return stats;
	}

    @Override
	public void onNext(Statistics stats) {
	    if (stats != null) {
		    this.stats = stats;
		    this.onComplete();
	    }
	    this.countDownLatch.countDown();
	}
    
    public boolean gotStats() {
    	return stats != null;
    }
    
    public void reset() {
    	stats = null;
    }

}
