<h1 align="center">
گزارش پروژه‌ی پایانی درس سامانه‌های بی‌درنگ
</h1>
<h2 align="center">
  دانشكده مهندسی کامپيوتر
  
  
   دانشگاه صنعتی شریف
</h2>
<h3 align="center">
   گروه 7: علیرضا تاجمیرریاحی، بهار خدابخشیان، درنا دهقانی
</h3>

### مقدمه
در این پروژه قصد داریم که چهار ساعت از چهار کشور متفاوت را روی چهار thread متفاوت نمایش دهیم. هر ساعت اولویتی دارد که می‌تواند ثابت و یا متغیر باشد. در هر زمان ابتدا مقدار مصرف CPU دستگاه محاسبه می‌شود و در صورت بالاتر بودن از حدی مشخص کار ساعت‌های با اولویت پایین‌تر متوقف می‌شود؛ بدین صورت که اگر مصرف CPU کمتر از 70درصد بود هر چهار ساعت فعالند، اگر بین 70 تا 90 درصد بود دو ساعت با اولویت بالاتر فعالند و اگر بیش از 90 درصد بود تنها ساعت با بالاترین اولویت کار می‌کند. برای پیاده‌سازی این پروژه از جاوا استفاده کردیم.   

### توضیحات فایل‌های پروژه

  - فایل `ExecutionLevels.java`: در این فایل تعداد ساعت‌ها با اولویت معین مشخص شده‌اند. یعنی هنگامی که در حالت HighestPriorityOnly باشیم تنها 1 ساعت اجازه کار کردن دارد و یا اگر در حالت AvailableForAll باشیم هر 4 ساعت فعالند.</li>
  - فایل `ClockThread.java`: برای هر ساعت به کمک این کلاس یک thread ساخته می‌شود که مطابق با موقعیت مکانی، ساعت محل را نشان می‌دهد.
  - فایل `DigitalWatch.java`: در این فایل تنظیمات گرافیکی ساعت‌ها قرار دارد و برای هر ساعت یک نمایش دیجیتالی به کمک swing و awt طراحی گردیده است که در قالب thread اجرا می‌گردند.
  - فایل `MockValues.java`: در صورت متغیر بودن اولویت ساعت‌ها، ترتیب‌هایی متغیر از اولویت آنها تولید می‌شود که هر 10 ثانیه یک بار تغییر کنند. هم‌چنین برای تست تاثیر مصرف CPU روی کارایی ساعت‌ها هر 10 ثانیه مقداری به آن اختصاص داده می‌شود تا کارایی سیستم قابل بررسی باشد. 
  - فایل `ClockPriorities.java`: اگر اولویت ساعت‌ها ثابت باشند، یک ترتیب مشخص و ثابت برای ترتیب اولویت آنها تعیین می‌گردد. در غیر اینصورت اولویت‌های متغیر تولید شده توسط Mock Values بیانگر این ترتیب اولویت‌هاست.
  - فایل `CPUUtilization.java`: ابتدا مقدار مصرف CPU دستگاه اندازه‌گیری می‌شود و سپس بر اساس آن تعداد ساعت‌های مجاز به کار مشخص می‌گردد.
  - فایل `Main.java`: اجرای پروژه از طریق این فایل امکان‌پذیر است. یک thread اصلی که بالاترین اولویت را دارد به شمارش زمان می‌پردازد و به ازای هر منطقه یک thread برای نمایش زمان آن ساخته می‌شود، بطوریکه مقدار شمارش در thread اصلی را با مقدار ثابت به ازای هر منطقه جمع می‌کند. هم‌چنین یک thread مجزا برای نمایش گرافیکی آن نیز ساخته می‌شود. بصورت پیش‌فرض اولویت‌هایی به این ساعت‌ها اختصاص می‌یابد که به کمک یک boolean (FIXED_PRIORITY) می‌توان متغیر یا ثابت بودن این اولویت‌ها را تعیین کرد. سپس در یک حلقه‌ی بی‌نهایت، با توجه به اولویت هر ساعت و هم‌چنین درصد مصرف CPU در هر ثانیه، محاسبه‌ی زمان  ساعت‌ها ادامه می‌یابد اما نمایش گرافیکی آنها در صورت بالا بودن مصرف CPU .متوقف خواهد شد.

![image](https://user-images.githubusercontent.com/45297279/180617631-8ae1dc32-a3a1-4013-ad40-df0f02b5bcb8.png)
