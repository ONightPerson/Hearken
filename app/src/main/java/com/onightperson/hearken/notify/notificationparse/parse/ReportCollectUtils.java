package com.onightperson.hearken.notify.notificationparse.parse;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;

import com.onightperson.hearken.notify.notificationparse.zakermodel.PushDataModel;
import com.onightperson.hearken.notify.notificationparse.model.a;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.onightperson.hearken.notify.notificationparse.model.Item;

/**
 * Created by tf on 8/2/2017.
 */
public class ReportCollectUtils {

    private static final String KEY_LOGIC_JUDGE = "LOGIC_JUDGE";
    private static final String VALUE_END = "end";

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private static final String REPORT_TAG = "REPORT=";

    private static final String PKG_TENCENT_NEWS = "com.tencent.news";
    private static final String PKG_TENCENT_READING = "com.tencent.reading";
    private static final String PKG_SOHU = "com.sohu.newsclient";
    private static final String PKG_TIME = "com.huanqiu.news";
    private static final String PKG_PEOPLE = "com.peopledailychina.activity";
    private static final String PKG_IFENG = "com.ifeng.news2";
    private static final String PKG_WONDERTEK = "com.wondertek.paper";
    private static final String PKG_CAI = "com.lanjinger.choiassociatedpress";
    private static final String PKG_UC = "com.uc.infoflow";
    private static final String PKG_XIN = "net.xinhuamm.mainclient";
    private static final String PKG_SINA = "com.sina.news";
    private static final String PKG_TOUTIAO = "com.ss.android.article.news";
    private static final String PKG_NETEASE = "com.netease.newsreader.activity";
    private static final String PKG_ZAKER = "com.myzaker.ZAKER_Phone";
    private static final String PKG_CNTV = "cn.cntvnews";
    private static final String PKG_YIDIAN = "com.hipu.yidian";

    public static Queue<String> queue = new LinkedBlockingQueue<>(10);

    static NotificationBean filterReportBean(NotificationBean bean, Intent intent) {
        if (null == intent) {
            return null;
        }

        Pair<String, String> result;
        switch (bean.pkg) {
            case PKG_TOUTIAO:         // 头条
                result = touTiaoUrl(intent);
                break;
            case PKG_NETEASE:         // 网易
                result = neteaseUrl(intent);
                break;
            case PKG_YIDIAN:          // 一点
                result = yidianUrl(intent);
                break;
            case PKG_CNTV:            // 央视
                result = cntvUrl(intent);
                break;
            case PKG_TENCENT_NEWS:    // 腾讯新闻
                result = qqNewsUrl(intent);
                break;
            case PKG_TENCENT_READING: // 天天快报
                result = qqReadingUrl(intent);
                break;
            case PKG_SOHU:            // 搜狐
                result = sohuUrl(intent);
                break;
            case PKG_TIME:            // 环球
                result = timeUrl(intent);
                break;
            case PKG_PEOPLE:          // 人民日报
                result = peopleUrl(intent);
                break;
            case PKG_IFENG:           // 凤凰
                result = fengUrl(intent);
                break;
            case PKG_WONDERTEK:       // 澎湃新闻
                result = wondertekUrl(intent);
                break;
            case PKG_CAI:             // 财联社
                result = caiUrl(intent);
                break;
            case PKG_UC:              // UC头条
                result = ucUrl(intent);
                break;
            case PKG_XIN:             // 新华社
                result = xhUrl(intent);
                break;
            case PKG_SINA:            // 新浪
                result = sinaUrl(intent);
                break;
            case PKG_ZAKER:
                result = zakerUrl(intent);
                break;
            default:
                result = null;
                break;
        }
        if (null == result) {
            return null;
        }

        NotificationBean cp = bean.copy();
        cp.appendInfoContent(REPORT_TAG, result);
        return cp;
    }

    // 腾讯新闻
    private static Pair<String, String> qqNewsUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String pushserviceid = bundle.getString("pushserviceid");
            if (!TextUtils.isEmpty(pushserviceid)) {
                return new Pair<>("Intent.EXTRA.pushserviceid[Concatenate]",
                        HTTP + "view.inews.qq.com/a/" + pushserviceid);
            }
        } else if (TextUtils.equals(intent.getComponent().flattenToShortString(),
                "com.tencent.news/.push.foreground.ForegroundEmptyActivity")) {
            return new Pair<>(KEY_LOGIC_JUDGE, VALUE_END);
        }
        return null;
    }

    // 天天快报
    private static Pair<String, String> qqReadingUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String pushserviceid = bundle.getString("pushserviceid");
            if (!TextUtils.isEmpty(pushserviceid)) {
                return new Pair<>("Intent.EXTRA.pushserviceid[Concatenate]",
                        HTTP + "kuaibao.qq.com/s/" + pushserviceid);
            }
        } else {
            return new Pair<>(KEY_LOGIC_JUDGE, VALUE_END);
        }
        return null;
    }

    // 搜狐
    private static Pair<String, String> sohuUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String urlLink = bundle.getString("urlLink");
            if (!TextUtils.isEmpty(urlLink)) {
                String[] params = urlLink.split("&");
                for (String param : params) {
                    if (param.startsWith("newsId")) {
                        String newId = param.split("=")[1];
                        return new Pair<>("Intent.EXTRA.urlLink[Concatenate]", HTTP + "3g.k.sohu.com/t/n" + newId);
                    }
                }
            }
        }
        return null;
    }

    // 环球time
    private static Pair<String, String> timeUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String news = bundle.getString("cn.jpush.android.EXTRA");
            JSONObject url;
            try {
                url = new JSONObject(URLDecoder.decode(news, "UTF-8"));
                return new Pair<>("Intent.EXTRA.cn.jpush.android.EXTRA:url", url.getString("url"));
            } catch (JSONException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    // 人民日报
    private static Pair<String, String> peopleUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String id = bundle.getString("id");
            if (!TextUtils.isEmpty(id)) {
                return new Pair<>("Intent.EXTRA.id[Concatenate]", HTTP
                        + "app.peopleapp.com/Api/600/DetailApi/shareArticle?type=0&article_id="
                        + id);
            }
        }
        return null;
    }

    // 凤凰新闻
    private static Pair<String, String> fengUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String newsId = bundle.getString("extra.com.ifeng.news2.id");
            if (!TextUtils.isEmpty(newsId)) {
                return new Pair<>("Intent.EXTRA.extra.com.ifeng.news2.id[Concatenate]",
                        HTTP + "share.iclient.ifeng.com/shareNews?aid=" + newsId);
            }
        }
        return null;
    }

    // 澎湃
    private static Pair<String, String> wondertekUrl(Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String msg = bundle.getString("MSG");
                JSONObject json = new JSONObject(URLDecoder.decode(msg, "UTF-8"));
                JSONObject body = json.getJSONObject("body");
                String custom = body.getString("custom");
                if (!TextUtils.isEmpty(custom)) {
                    int index = custom.trim().indexOf("|");
                    String id = custom.substring(index + 1);
                    return new Pair<>("Intent.EXTRA.MSG[json.body.custom]",
                            HTTP + "m.thepaper.cn/newsDetail_forward_" + id);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 财联社
    private static Pair<String, String> caiUrl(Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String schema = bundle.getString("schema");
                if (!TextUtils.isEmpty(schema)) {
                    String urlDecode = URLDecoder.decode(schema, "UTF-8");
                    String detailId = urlDecode.split("\\?")[1].split("=")[1];
                    return new Pair<>("Intent.EXTRA.schema[Concatenate]", HTTPS
                            + "api2.cailianpress.com/v1/share/article_detail?article_id="
                            + detailId);
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // UC头条
    private static Pair<String, String> ucUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String url = bundle.getString("url");
            if (!TextUtils.isEmpty(url)) {
                return new Pair<>("Intent.EXTRA.url", url);
            }
        }
        return null;
    }

    // 新华社
    private static Pair<String, String> xhUrl(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String id = bundle.getString("id");
            if (!TextUtils.isEmpty(id)) {
                return new Pair<>("Intent.Extra.id[Concatenate]", HTTPS
                        + "xhpfmapi.zhongguowangshi.com/detail/index.html?docid=" + id);
            }
        }
        return null;
    }

    // 新浪
    private static Pair<String, String> sinaUrl(Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Intent sinaBundle = bundle.getParcelable("realIntent");
                if (sinaBundle != null) {
                    String sinaJson = sinaBundle.getStringExtra("RouteInfo");
                    JSONObject json = new JSONObject(sinaJson);
                    return new Pair<>("Intent.EXTRA.realIntent[Intent].RouteInfo", json.getString("link"));
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 今日头条
    private static Pair<String, String> touTiaoUrl(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            String groupId = uri.toString().split("=")[1];
            if (!TextUtils.isEmpty(groupId)) {
                return new Pair<>("Intent.Data[SPLIT]", HTTP + "toutiao.com/group/" + groupId);
            }
        } else {
            throw new RuntimeException("groupId not found");
        }
        return null;
    }

    // 网易
    private static Pair<String, String> neteaseUrl(Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if (action.equals("com.netease.newsreader.activity.push.receiver")) {
                ClassLoader loader = ReportCollectUtils.class.getClassLoader();
                intent.setExtrasClassLoader(loader);
                a a = intent.getParcelableExtra("bean_push");
                String id = a.getA();
                String category = a.getJ();
                if (!TextUtils.isEmpty(id)) {
                    if (TextUtils.equals(category, "docid")) {
                        return new Pair<>("Intent.EXTRA.bean_push[com.netease.nr.biz.push.newpush.a]",
                                HTTPS + "c.m.163.com/news/a/" + id + ".html");
                    } else if (TextUtils.equals(category, "sid")) {
                        return new Pair<>("Intent.EXTRA.bean_push[com.netease.nr.biz.push.newpush.a]",
                                HTTPS + "c.m.163.com/news/s/" + id + ".html");
                    }
                }
            } else if (TextUtils.equals(action, "com.netease.newsreader.activity.update.receiver")) {
                return new Pair<>(KEY_LOGIC_JUDGE, VALUE_END);
            }
        }
        return null;
    }

    // 一点
    @SuppressWarnings("unchecked")
    private static Pair<String, String> yidianUrl(Intent intent) {
        try {
            Class clazz = Class.forName("Invoke");
            Method yiMethod = clazz.getMethod("invoke2bul", Intent.class);
            String docid = (String) yiMethod.invoke(clazz, intent);
            if (!TextUtils.isEmpty(docid)) {
                return new Pair<>("CLASS:Invoke.invoke2bul(Intent)", "www.yidianzixun.com/article/" + docid);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 央视
    private static Pair<String, String> cntvUrl(Intent intent) {
        Item item = (Item) intent.getSerializableExtra(Item.class.getName());
        if (item != null && TextUtils.equals(item.getItemType(), "article_flag")) {
            String url = item.getDetailUrl();
            if (url != null) {
                return new Pair<>("Intent.EXTRA[cn.cntvnews.entity.Item.detailUrl]", url);
            }
        }
        return null;
    }

    private static Pair<String, String> zakerUrl(Intent intent) {
        PushDataModel model;
        try {
            ClassLoader loader = ReportCollectUtils.class.getClassLoader();
            intent.setExtrasClassLoader(loader);
            model = intent.getParcelableExtra("pushmodel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String url = "";
        if (model != null) {
            if (TextUtils.equals(model.getType(), "a")) {
                url = model.getArticleStaticUrl();
                queue.poll();
            }
        } else {
            url = queue.poll();
        }
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (url.startsWith("http")) {
            url = url.replace("http", "https");
        }
        String response = getCallable(url);
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject object = new JSONObject(response);
                JSONObject data = object.optJSONObject("data");
                JSONArray array = data.optJSONArray("articles");
                JSONObject article = array.getJSONObject(0);
                new Pair<>("Intent.EXTRA.pushmodel[com.myzaker.ZAKER_Phone.model.apimodel.PushDataModel].queryServer",
                        article.optString("weburl"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private static String getCallable(String url) {
        Callable<String> call = new UrlCallableClass(url);
        FutureTask<String> future = new FutureTask<>(call);
        new Thread(future).start();
        String result;
        try {
            result = future.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static class UrlCallableClass implements Callable<String> {
        private String url;

        UrlCallableClass(String url) {
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            return get(url);
        }

        private static String getStringFromInputStream(InputStream is) throws IOException {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            is.close();
            return os.toString();
        }

        private static String get(String url) {
            HttpURLConnection conn = null;
            try {
                // 利用string url构建URL对象
                URL mURL = new URL(url);
                conn = (HttpURLConnection) mURL.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    InputStream is = conn.getInputStream();
                    return getStringFromInputStream(is);
                } else {
                    throw new NetworkErrorException("response status is " + responseCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return null;
        }
    }
}
