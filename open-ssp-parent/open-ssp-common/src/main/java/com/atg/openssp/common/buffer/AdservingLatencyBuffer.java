package com.atg.openssp.common.buffer;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atg.openssp.common.configuration.Context;

/**
 * @author André Schmer
 */
public class AdservingLatencyBuffer extends LongTypedBuffer implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(AdservingLatencyBuffer.class);

	private static final AdservingLatencyBuffer instance = new AdservingLatencyBuffer();

	public static AdservingLatencyBuffer getBuffer() {
		return instance;
	}

	private AdservingLatencyBuffer() {
		super(100);
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			latch.await();
		} catch (final InterruptedException e) {
			log.error("{} latch interrupted", LocalDate.now(Context.zoneId));
		}
		Thread.currentThread().interrupt();
	}

}
