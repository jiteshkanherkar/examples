package org.jitesh.examples.restapispringboot.boot;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.annotation.PostConstruct;

import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationWatcher{

	private String filePath;
	private ConfigurableApplicationContext applicationContext;

	public ApplicationWatcher(String filePath, ConfigurableApplicationContext applicationContext) {
		super();
		this.filePath = filePath;
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void startWatcher() {
		System.out.println("App Watcher Thread starting...");
		Thread thread=new Thread(new WatcherRunnable());
		thread.setDaemon(true);
		thread.start();
		System.out.println("App Watcher Thread initialzed...");
	}
	private class WatcherRunnable implements Runnable {
		@Override
		public void run() {
			try {
				Path path = Paths.get(filePath);
				WatchService watcher = FileSystems.getDefault().newWatchService();
				Path dir = path.getParent();
				dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

				System.out.println("Watch Service registered for dir: " + dir.getFileName());

				while (true) {
					WatchKey key;
					try {
						key = watcher.take();
					} catch (InterruptedException ex) {
						return;
					}

					for (WatchEvent<?> event : key.pollEvents()) {
						WatchEvent.Kind<?> kind = event.kind();

						@SuppressWarnings("unchecked")
						WatchEvent<Path> ev = (WatchEvent<Path>) event;
						Path fileName = ev.context();

						System.out.println(kind.name() + ": " + fileName);

						if (kind == ENTRY_MODIFY && fileName.toString().equals("pid.file")) {
							System.out.println("pid file has changed!!!");
						} else if (kind == ENTRY_DELETE && fileName.toString().equals("pid.file")) {
							System.out.println("pid file has deleted!!!");
							applicationContext.close();
						}
					}

					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				}

			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

}
