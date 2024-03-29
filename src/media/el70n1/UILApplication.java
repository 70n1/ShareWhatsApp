package media.el70n1;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class UILApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
			.threadPoolSize(3)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.memoryCacheSize(1500000) // 1.5 Mb
			.httpReadTimeout(10000) // 10 s
			.denyCacheImageMultipleSizesInMemory()
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.enableLogging() // Not necessary in common
			.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		Log.v("dddd", "iniciando....");
	}
}