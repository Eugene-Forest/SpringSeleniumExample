# Bot Web API

## warning `Only local connections are allowed.`
通过api调用自动化测试服务，这里会有一个问题

```cmd
Starting ChromeDriver 129.0.6668.100 (cf58cba358d31ce285c1970a79a9411d0fb381a5-refs/branch-heads/6668@{#1704}) on port 61454
Only local connections are allowed.
Please see https://chromedriver.chromium.org/security-considerations for suggestions on keeping ChromeDriver safe.
ChromeDriver was started successfully on port 61454.
```

指定后会出现 `Only local connections are allowed.` 的警告。

由于目前还没有涉及使用 selenium grip，暂时不做处理。

## For WebDriver

谷歌驱动器有一下链接可下载：

* https://googlechromelabs.github.io/chrome-for-testing/#stable

* https://getwebdriver.com/chromedriver#stable

