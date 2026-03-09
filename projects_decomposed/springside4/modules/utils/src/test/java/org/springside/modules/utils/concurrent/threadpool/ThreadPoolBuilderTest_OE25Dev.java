package org.springside.modules.utils.concurrent.threadpool;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springside.modules.utils.concurrent.threadpool.QueuableCachedThreadPool.ControllableQueue;

public class ThreadPoolBuilderTest_OE25Dev {

	@Test
	public void fixPool() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(1);
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(1);
		assertThat(singlePool.getQueue()).isInstanceOf(LinkedBlockingQueue.class);
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		assertThat(fixPoolWithUnlimitQueue.getCorePoolSize()).isEqualTo(10);
		assertThat(fixPoolWithUnlimitQueue.getMaximumPoolSize()).isEqualTo(10);
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		assertThat(fixPoolWithlimitQueue.getQueue()).isInstanceOf(ArrayBlockingQueue.class);
		Thread thread = fixPoolWithlimitQueue.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("kaka");

		fixPoolWithlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPool").build();
		Thread thread2 = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread2.getName()).startsWith("fixPool");
		assertThat(thread2.isDaemon()).isFalse();
		fixPoolWithNamePrefix.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefixAndDaemon = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPoolDaemon").setDaemon(true).build();
		Thread thread3 = fixPoolWithNamePrefixAndDaemon.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread3.getName()).startsWith("fixPoolDaemon");
		assertThat(thread3.isDaemon()).isTrue();
		fixPoolWithNamePrefixAndDaemon.shutdown();
	}

	@Test
	public void cachedPool() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(0);
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
		assertThat(singlePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(10);
		assertThat(singlePool.getQueue()).isInstanceOf(SynchronousQueue.class);
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.cachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getCorePoolSize()).isEqualTo(10);
		assertThat(sizeablePool.getMaximumPoolSize()).isEqualTo(100);
		assertThat(sizeablePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(20);
		sizeablePool.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.cachedPool().setThreadNamePrefix("cachedPool")
				.build();
		Thread thread = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {

			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("cachedPool");
		fixPoolWithNamePrefix.shutdown();
	}

	@Test
	public void scheduledPool() {
		ScheduledThreadPoolExecutor singlePool = ThreadPoolBuilder.scheduledPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(1);
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
		singlePool.shutdown();

		ScheduledThreadPoolExecutor sizeablePool = ThreadPoolBuilder.scheduledPool().setPoolSize(2).build();
		assertThat(sizeablePool.getCorePoolSize()).isEqualTo(2);
		assertThat(sizeablePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
		sizeablePool.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.scheduledPool()
				.setThreadNamePrefix("scheduledPool").build();
		Thread thread = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("scheduledPool");
		fixPoolWithNamePrefix.shutdown();
	}

	@Test
	public void quequablePool() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(0);
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
		assertThat(singlePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(10);
		assertThat(singlePool.getQueue()).isInstanceOf(ControllableQueue.class);
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.queuableCachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getCorePoolSize()).isEqualTo(10);
		assertThat(sizeablePool.getMaximumPoolSize()).isEqualTo(100);
		assertThat(sizeablePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(20);
		sizeablePool.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.queuableCachedPool()
				.setThreadNamePrefix("queuableCachedPool").build();
		Thread thread = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {

			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("queuableCachedPool");
		fixPoolWithNamePrefix.shutdown();
	}

	@Test
	public void fixPool_1_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(1);
	}

	@Test
	public void fixPool_2_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(1);
	}

	@Test
	public void fixPool_3_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		assertThat(singlePool.getQueue()).isInstanceOf(LinkedBlockingQueue.class);
	}

	@Test
	public void fixPool_4_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		assertThat(fixPoolWithUnlimitQueue.getCorePoolSize()).isEqualTo(10);
	}

	@Test
	public void fixPool_5_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		assertThat(fixPoolWithUnlimitQueue.getMaximumPoolSize()).isEqualTo(10);
	}

	@Test
	public void fixPool_6_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		assertThat(fixPoolWithlimitQueue.getQueue()).isInstanceOf(ArrayBlockingQueue.class);
	}

	@Test
	public void fixPool_7_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		Thread thread = fixPoolWithlimitQueue.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("kaka");
	}

	@Test
	public void fixPool_8_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		Thread thread = fixPoolWithlimitQueue.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});

		fixPoolWithlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPool").build();
		Thread thread2 = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread2.getName()).startsWith("fixPool");
	}

	@Test
	public void fixPool_9_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		Thread thread = fixPoolWithlimitQueue.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});

		fixPoolWithlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPool").build();
		Thread thread2 = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread2.isDaemon()).isFalse();
	}

	@Test
	public void fixPool_10_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		Thread thread = fixPoolWithlimitQueue.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});

		fixPoolWithlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPool").build();
		Thread thread2 = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		fixPoolWithNamePrefix.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefixAndDaemon = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPoolDaemon").setDaemon(true).build();
		Thread thread3 = fixPoolWithNamePrefixAndDaemon.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread3.getName()).startsWith("fixPoolDaemon");
	}

	@Test
	public void fixPool_11_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.fixedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor fixPoolWithUnlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).build();
		fixPoolWithUnlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithlimitQueue = ThreadPoolBuilder.fixedPool().setPoolSize(10).setQueueSize(100)
				.setThreadFactory(ThreadPoolUtil.buildThreadFactory("kaka")).build();

		Thread thread = fixPoolWithlimitQueue.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});

		fixPoolWithlimitQueue.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPool").build();
		Thread thread2 = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		fixPoolWithNamePrefix.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefixAndDaemon = ThreadPoolBuilder.fixedPool().setPoolSize(10)
				.setThreadNamePrefix("fixPoolDaemon").setDaemon(true).build();
		Thread thread3 = fixPoolWithNamePrefixAndDaemon.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread3.isDaemon()).isTrue();
	}

	@Test
	public void cachedPool_1_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(0);
	}

	@Test
	public void cachedPool_2_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void cachedPool_3_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		assertThat(singlePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(10);
	}

	@Test
	public void cachedPool_4_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		assertThat(singlePool.getQueue()).isInstanceOf(SynchronousQueue.class);
	}

	@Test
	public void cachedPool_5_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.cachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getCorePoolSize()).isEqualTo(10);
	}

	@Test
	public void cachedPool_6_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.cachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getMaximumPoolSize()).isEqualTo(100);
	}

	@Test
	public void cachedPool_7_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.cachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(20);
	}

	@Test
	public void cachedPool_8_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.cachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.cachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		sizeablePool.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.cachedPool().setThreadNamePrefix("cachedPool")
				.build();
		Thread thread = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {

			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("cachedPool");
	}

	@Test
	public void scheduledPool_1_oe() {
		ScheduledThreadPoolExecutor singlePool = ThreadPoolBuilder.scheduledPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(1);
	}

	@Test
	public void scheduledPool_2_oe() {
		ScheduledThreadPoolExecutor singlePool = ThreadPoolBuilder.scheduledPool().build();
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void scheduledPool_3_oe() {
		ScheduledThreadPoolExecutor singlePool = ThreadPoolBuilder.scheduledPool().build();
		singlePool.shutdown();

		ScheduledThreadPoolExecutor sizeablePool = ThreadPoolBuilder.scheduledPool().setPoolSize(2).build();
		assertThat(sizeablePool.getCorePoolSize()).isEqualTo(2);
	}

	@Test
	public void scheduledPool_4_oe() {
		ScheduledThreadPoolExecutor singlePool = ThreadPoolBuilder.scheduledPool().build();
		singlePool.shutdown();

		ScheduledThreadPoolExecutor sizeablePool = ThreadPoolBuilder.scheduledPool().setPoolSize(2).build();
		assertThat(sizeablePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void scheduledPool_5_oe() {
		ScheduledThreadPoolExecutor singlePool = ThreadPoolBuilder.scheduledPool().build();
		singlePool.shutdown();

		ScheduledThreadPoolExecutor sizeablePool = ThreadPoolBuilder.scheduledPool().setPoolSize(2).build();
		sizeablePool.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.scheduledPool()
				.setThreadNamePrefix("scheduledPool").build();
		Thread thread = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {
			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("scheduledPool");
	}

	@Test
	public void quequablePool_1_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		assertThat(singlePool.getCorePoolSize()).isEqualTo(0);
	}

	@Test
	public void quequablePool_2_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		assertThat(singlePool.getMaximumPoolSize()).isEqualTo(Integer.MAX_VALUE);
	}

	@Test
	public void quequablePool_3_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		assertThat(singlePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(10);
	}

	@Test
	public void quequablePool_4_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		assertThat(singlePool.getQueue()).isInstanceOf(ControllableQueue.class);
	}

	@Test
	public void quequablePool_5_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.queuableCachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getCorePoolSize()).isEqualTo(10);
	}

	@Test
	public void quequablePool_6_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.queuableCachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getMaximumPoolSize()).isEqualTo(100);
	}

	@Test
	public void quequablePool_7_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.queuableCachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		assertThat(sizeablePool.getKeepAliveTime(TimeUnit.SECONDS)).isEqualTo(20);
	}

	@Test
	public void quequablePool_8_oe() {
		ThreadPoolExecutor singlePool = ThreadPoolBuilder.queuableCachedPool().build();
		singlePool.shutdown();

		ThreadPoolExecutor sizeablePool = ThreadPoolBuilder.queuableCachedPool().setMinSize(10).setMaxSize(100)
				.setKeepAliveSecs(20).build();
		sizeablePool.shutdown();

		ThreadPoolExecutor fixPoolWithNamePrefix = ThreadPoolBuilder.queuableCachedPool()
				.setThreadNamePrefix("queuableCachedPool").build();
		Thread thread = fixPoolWithNamePrefix.getThreadFactory().newThread(new Runnable() {

			@Override
			public void run() {
			}
		});
		assertThat(thread.getName()).startsWith("queuableCachedPool");
	}

}
