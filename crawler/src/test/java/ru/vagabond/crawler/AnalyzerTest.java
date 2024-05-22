package ru.vagabond.crawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vagabond.crawler.utils.Analyzer;

import java.util.Set;

public class AnalyzerTest {

    private static final String TELEGRAPH_URL_BODY =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>北京天使 目录 – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="北京天使 目录">
                                <meta property="og:description" content="北京天使 2017 五月刊 [31P]">
                                <meta property="og:image" content="">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2022-09-24T06:51:58+0000">
                                <meta property="article:modified_time" content="2022-09-24T06:51:58+0000">
                                <meta property="article:author" content="搬运计划">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="北京天使 目录">
                                <meta name="twitter:description" content="北京天使 2017 五月刊 [31P]">
                                <meta name="twitter:image" content="">
                                <link rel="canonical" href="https://telegra.ph/北京天使-目录-09-24" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>北京天使 目录</h1>
                                        <address>
                                          <a rel="author" href="https://t.me/sese_MoMo" target="_blank">搬运计划</a><!--
                                       --><time datetime="2022-09-24T06:51:58+0000">September 24, 2022</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>北京天使 目录<br></h1><address><a href="https://t.me/sese_MoMo" target="_blank">搬运计划</a><br></address><p>北京天使 2017 五月刊 [31P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-2017-%E4%BA%94%E6%9C%88%E5%88%8A-31P-09-22">https://telegra.ph/北京天使-2017-五月刊-31P-09-22 <br><br></a><p>北京天使 2018 一月刊 上 [9P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-2018-%E4%B8%80%E6%9C%88%E5%88%8A-%E4%B8%8A-9P-09-22">https://telegra.ph/北京天使-2018-一月刊-上-9P-09-22 <br><br></a><p>北京天使 2018 一月刊 下 [9P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-2018-%E4%B8%80%E6%9C%88%E5%88%8A-%E4%B8%8B-9P-09-22">https://telegra.ph/北京天使-2018-一月刊-下-9P-09-22 <br><br></a><p>北京天使 2018 一月刊 中 [9P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-2018-%E4%B8%80%E6%9C%88%E5%88%8A-%E4%B8%AD-9P-09-22">https://telegra.ph/北京天使-2018-一月刊-中-9P-09-22 <br><br></a><p>79 </p><a href="/79-09-22">https://telegra.ph/79-09-22 <br><br></a><p>82 </p><a href="/82-09-22">https://telegra.ph/82-09-22 <br><br></a><p>84 </p><a href="/84-09-22">https://telegra.ph/84-09-22 <br><br></a><p>89 </p><a href="/89-09-22">https://telegra.ph/89-09-22 <br><br></a><p>北京天使 Ariel 2018.11.09《今日美术馆》露出 [20P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20181109%E4%BB%8A%E6%97%A5%E7%BE%8E%E6%9C%AF%E9%A6%86%E9%9C%B2%E5%87%BA-20P-09-22">https://telegra.ph/北京天使-Ariel-20181109今日美术馆露出-20P-09-22 <br><br></a><p>北京天使 Ariel 2018.12.02《美术馆Part 2》露出 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20181202%E7%BE%8E%E6%9C%AF%E9%A6%86Part-2%E9%9C%B2%E5%87%BA-16P-09-22">https://telegra.ph/北京天使-Ariel-20181202美术馆Part-2露出-16P-09-22 <br><br></a><p>北京天使 Ariel 2018.12.03《美术馆Part 3》露出 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20181203%E7%BE%8E%E6%9C%AF%E9%A6%86Part-3%E9%9C%B2%E5%87%BA-16P-09-22">https://telegra.ph/北京天使-Ariel-20181203美术馆Part-3露出-16P-09-22 <br><br></a><p>北京天使 Ariel 2018.12.04《美术馆Part 1》露出 [14P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20181204%E7%BE%8E%E6%9C%AF%E9%A6%86Part-1%E9%9C%B2%E5%87%BA-14P-09-22">https://telegra.ph/北京天使-Ariel-20181204美术馆Part-1露出-14P-09-22 <br><br></a><p>北京天使 Ariel 2019.01.02 《天坛》 [15P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190102-%E5%A4%A9%E5%9D%9B-15P-09-22">https://telegra.ph/北京天使-Ariel-20190102-天坛-15P-09-22 <br><br></a><p>北京天使 Ariel 2019.01.02 《庆丰公园》 [15P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190102-%E5%BA%86%E4%B8%B0%E5%85%AC%E5%9B%AD-15P-09-22">https://telegra.ph/北京天使-Ariel-20190102-庆丰公园-15P-09-22 <br><br></a><p>北京天使 Ariel 2019.01.02 《红领巾桥》 [27P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190102-%E7%BA%A2%E9%A2%86%E5%B7%BE%E6%A1%A5-27P-09-22">https://telegra.ph/北京天使-Ariel-20190102-红领巾桥-27P-09-22 <br><br></a><p>北京天使 Ariel 2019.01.02 《金源-广场物语》 [17P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190102-%E9%87%91%E6%BA%90-%E5%B9%BF%E5%9C%BA%E7%89%A9%E8%AF%AD-17P-09-22">https://telegra.ph/北京天使-Ariel-20190102-金源-广场物语-17P-09-22 <br><br></a><p>北京天使 Ariel 2019.01.15 《王府中环》 [12P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190115-%E7%8E%8B%E5%BA%9C%E4%B8%AD%E7%8E%AF-12P-09-22">https://telegra.ph/北京天使-Ariel-20190115-王府中环-12P-09-22 <br><br></a><p>北京天使 Ariel 2019.01.21 《屋顶》 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190121-%E5%B1%8B%E9%A1%B6-16P-09-22">https://telegra.ph/北京天使-Ariel-20190121-屋顶-16P-09-22 <br><br></a><p>北京天使 Ariel 2019.02.20 《鼓浪屿北01》 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190220-%E9%BC%93%E6%B5%AA%E5%B1%BF%E5%8C%9701-16P-09-22">https://telegra.ph/北京天使-Ariel-20190220-鼓浪屿北01-16P-09-22 <br><br></a><p>北京天使 Ariel 2019.03.11 《华熙party-步行街》 [18P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190311-%E5%8D%8E%E7%86%99party-%E6%AD%A5%E8%A1%8C%E8%A1%97-18P-09-22">https://telegra.ph/北京天使-Ariel-20190311-华熙party-步行街-18P-09-22 <br><br></a><p>北京天使 Ariel 2019.03.12 《大悦城 极限露出》 [24P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190312-%E5%A4%A7%E6%82%A6%E5%9F%8E-%E6%9E%81%E9%99%90%E9%9C%B2%E5%87%BA-24P-09-22">https://telegra.ph/北京天使-Ariel-20190312-大悦城-极限露出-24P-09-22 <br><br></a><p>北京天使 Ariel 2019.03.12 《极限露出之西单大悦城》 [10P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190312-%E6%9E%81%E9%99%90%E9%9C%B2%E5%87%BA%E4%B9%8B%E8%A5%BF%E5%8D%95%E5%A4%A7%E6%82%A6%E5%9F%8E-10P-09-22">https://telegra.ph/北京天使-Ariel-20190312-极限露出之西单大悦城-10P-09-22 <br><br></a><p>北京天使 Ariel 2019.03.14 《裸游野狸岛》 [13P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190314-%E8%A3%B8%E6%B8%B8%E9%87%8E%E7%8B%B8%E5%B2%9B-13P-09-22">https://telegra.ph/北京天使-Ariel-20190314-裸游野狸岛-13P-09-22 <br><br></a><p>67 </p><a href="/67-09-22-2">https://telegra.ph/67-09-22-2 <br><br></a><p>68 </p><a href="/68-09-22">https://telegra.ph/68-09-22 <br><br></a><p>北京天使 Ariel 2019.03.20 《Ariel的日常露出》 [17P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190320-Ariel%E7%9A%84%E6%97%A5%E5%B8%B8%E9%9C%B2%E5%87%BA-17P-09-22">https://telegra.ph/北京天使-Ariel-20190320-Ariel的日常露出-17P-09-22 <br><br></a><p>北京天使 Ariel 2019.03.23 《合生汇》 [20P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190323-%E5%90%88%E7%94%9F%E6%B1%87-20P-09-22">https://telegra.ph/北京天使-Ariel-20190323-合生汇-20P-09-22 <br><br></a><p>北京天使 Ariel 2019.03.26 《长楹天街》露出 [30P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-20190326-%E9%95%BF%E6%A5%B9%E5%A4%A9%E8%A1%97%E9%9C%B2%E5%87%BA-30P-09-22">https://telegra.ph/北京天使-Ariel-20190326-长楹天街露出-30P-09-22 <br><br></a><p>北京天使 Ariel 《首都图书馆露出》[18P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Ariel-%E9%A6%96%E9%83%BD%E5%9B%BE%E4%B9%A6%E9%A6%86%E9%9C%B2%E5%87%BA18P-09-22">https://telegra.ph/北京天使-Ariel-首都图书馆露出18P-09-22 <br><br></a><p>北京天使 Solana-Ariel&amp;momobaby1106 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Solana-Arielmomobaby1106-16P-09-22">https://telegra.ph/北京天使-Solana-Arielmomobaby1106-16P-09-22 <br><br></a><p>北京天使 Yuka 2019.02.18 《集美航海学院》 [21P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Yuka-20190218-%E9%9B%86%E7%BE%8E%E8%88%AA%E6%B5%B7%E5%AD%A6%E9%99%A2-21P-09-22">https://telegra.ph/北京天使-Yuka-20190218-集美航海学院-21P-09-22 <br><br></a><p>北京天使 Yuka 2019.02.22 《生态广场》 [21P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Yuka-20190222-%E7%94%9F%E6%80%81%E5%B9%BF%E5%9C%BA-21P-09-22">https://telegra.ph/北京天使-Yuka-20190222-生态广场-21P-09-22 <br><br></a><p>北京天使 Yuka 2019.02.28 《小黄人》 [17P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Yuka-20190228-%E5%B0%8F%E9%BB%84%E4%BA%BA-17P-09-22">https://telegra.ph/北京天使-Yuka-20190228-小黄人-17P-09-22 <br><br></a><p>北京天使 Yuka 2019.03.04 《深业上城（上）》 [21P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Yuka-20190304-%E6%B7%B1%E4%B8%9A%E4%B8%8A%E5%9F%8E%E4%B8%8A-21P-09-22">https://telegra.ph/北京天使-Yuka-20190304-深业上城上-21P-09-22 <br><br></a><p>64 </p><a href="/64-09-22">https://telegra.ph/64-09-22 <br><br></a><p>80 </p><a href="/80-09-22">https://telegra.ph/80-09-22 <br><br></a><p>北京天使 Yuka 2019.03.28 《新圆明园极限露出01》 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Yuka-20190328-%E6%96%B0%E5%9C%86%E6%98%8E%E5%9B%AD%E6%9E%81%E9%99%90%E9%9C%B2%E5%87%BA01-16P-09-22">https://telegra.ph/北京天使-Yuka-20190328-新圆明园极限露出01-16P-09-22 <br><br></a><p>75 </p><a href="/75-09-22">https://telegra.ph/75-09-22 <br><br></a><p>北京天使 Yuka 2019.03.28 《新圆明园极限露出03》 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Yuka-20190328-%E6%96%B0%E5%9C%86%E6%98%8E%E5%9B%AD%E6%9E%81%E9%99%90%E9%9C%B2%E5%87%BA03-16P-09-22">https://telegra.ph/北京天使-Yuka-20190328-新圆明园极限露出03-16P-09-22 <br><br></a><p>北京天使 《Denstinon公交车》露出 [10P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-Denstinon%E5%85%AC%E4%BA%A4%E8%BD%A6%E9%9C%B2%E5%87%BA-10P-09-22">https://telegra.ph/北京天使-Denstinon公交车露出-10P-09-22 <br><br></a><p>北京天使 《SOHO尚都》露出 [11P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-SOHO%E5%B0%9A%E9%83%BD%E9%9C%B2%E5%87%BA-11P-09-22">https://telegra.ph/北京天使-SOHO尚都露出-11P-09-22 <br><br></a><p>北京天使 《利星行广场》露出 [14P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E5%88%A9%E6%98%9F%E8%A1%8C%E5%B9%BF%E5%9C%BA%E9%9C%B2%E5%87%BA-14P-09-22">https://telegra.ph/北京天使-利星行广场露出-14P-09-22 <br><br></a><p>北京天使 《办公楼外》露出 [20P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E5%8A%9E%E5%85%AC%E6%A5%BC%E5%A4%96%E9%9C%B2%E5%87%BA-20P-09-22">https://telegra.ph/北京天使-办公楼外露出-20P-09-22 <br><br></a><p>北京天使 《北京商务中心》露出 [15P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E5%8C%97%E4%BA%AC%E5%95%86%E5%8A%A1%E4%B8%AD%E5%BF%83%E9%9C%B2%E5%87%BA-15P-09-22">https://telegra.ph/北京天使-北京商务中心露出-15P-09-22 <br><br></a><p>北京天使 《北京国贸中心》露出 [23P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E5%8C%97%E4%BA%AC%E5%9B%BD%E8%B4%B8%E4%B8%AD%E5%BF%83%E9%9C%B2%E5%87%BA-23P-09-22">https://telegra.ph/北京天使-北京国贸中心露出-23P-09-22 <br><br></a><p>北京天使 《医院冒险》露出 [15P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E5%8C%BB%E9%99%A2%E5%86%92%E9%99%A9%E9%9C%B2%E5%87%BA-15P-09-22">https://telegra.ph/北京天使-医院冒险露出-15P-09-22 <br><br></a><p>北京天使 《宜家家居》露出 [16P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E5%AE%9C%E5%AE%B6%E5%AE%B6%E5%B1%85%E9%9C%B2%E5%87%BA-16P-09-22">https://telegra.ph/北京天使-宜家家居露出-16P-09-22 <br><br></a><p>新建文件夹 </p><a href="/%E6%96%B0%E5%BB%BA%E6%96%87%E4%BB%B6%E5%A4%B9-09-22-2">https://telegra.ph/新建文件夹-09-22-2 <br><br></a><p>北京天使 《朦胧夏日》露出 [20P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E6%9C%A6%E8%83%A7%E5%A4%8F%E6%97%A5%E9%9C%B2%E5%87%BA-20P-09-22">https://telegra.ph/北京天使-朦胧夏日露出-20P-09-22 <br><br></a><p>北京天使 《清晨公园2049》露出 [12P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E6%B8%85%E6%99%A8%E5%85%AC%E5%9B%AD2049%E9%9C%B2%E5%87%BA-12P-09-22">https://telegra.ph/北京天使-清晨公园2049露出-12P-09-22 <br><br></a><p>北京天使 《购物中心唱吧》露出 [26P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E8%B4%AD%E7%89%A9%E4%B8%AD%E5%BF%83%E5%94%B1%E5%90%A7%E9%9C%B2%E5%87%BA-26P-09-22">https://telegra.ph/北京天使-购物中心唱吧露出-26P-09-22 <br><br></a><p>北京天使 《铁路博物馆》露出 [24P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E9%93%81%E8%B7%AF%E5%8D%9A%E7%89%A9%E9%A6%86%E9%9C%B2%E5%87%BA-24P-09-22">https://telegra.ph/北京天使-铁路博物馆露出-24P-09-22 <br><br></a><p>北京天使 《鸟巢体育场》露出 [17P] </p><a href="/%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E9%B8%9F%E5%B7%A2%E4%BD%93%E8%82%B2%E5%9C%BA%E9%9C%B2%E5%87%BA-17P-09-22">https://telegra.ph/北京天使-鸟巢体育场露出-17P-09-22 <br><br></a><p>102 </p><a href="/102-09-22">https://telegra.ph/102-09-22 <br><br></a><p>106 </p><a href="/106-09-22">https://telegra.ph/106-09-22 <br><br></a><p>108 </p><a href="/108-09-22">https://telegra.ph/108-09-22 <br><br></a><p>117 </p><a href="/117-09-22-2">https://telegra.ph/117-09-22-2 <br><br></a><p>104 </p><a href="/104-09-22">https://telegra.ph/104-09-22 <br><br></a><p>107 </p><a href="/107-09-22">https://telegra.ph/107-09-22 <br><br></a><p>83 </p><a href="/83-09-22-3">https://telegra.ph/83-09-22-3 <br><br></a><p>98 </p><a href="/98-09-22-2">https://telegra.ph/98-09-22-2 <br><br></a><p>97 </p><a href="/97-09-22-2">https://telegra.ph/97-09-22-2 <br><br></a><p>113 </p><a href="/113-09-22">https://telegra.ph/113-09-22 <br><br></a><p>114 </p><a href="/114-09-22">https://telegra.ph/114-09-22 <br><br></a><p>112 </p><a href="/112-09-22">https://telegra.ph/112-09-22 <br><br></a><p>115 </p><a href="/115-09-22">https://telegra.ph/115-09-22 <br><br></a><p>74 </p><a href="/74-09-22">https://telegra.ph/74-09-22 <br><br></a><p>119 </p><a href="/119-09-22">https://telegra.ph/119-09-22 <br><br></a><p>95 </p><a href="/95-09-22">https://telegra.ph/95-09-22 <br><br></a><p>109 (1) </p><a href="/109-1-09-22">https://telegra.ph/109-1-09-22 <br><br></a><p>96 </p><a href="/96-09-22-3">https://telegra.ph/96-09-22-3 <br><br></a><p>81 </p><a href="/81-09-22">https://telegra.ph/81-09-22 <br><br></a><p>94 </p><a href="/94-09-22-2">https://telegra.ph/94-09-22-2 <br><br></a><p>118 </p><a href="/118-09-22-4">https://telegra.ph/118-09-22-4 <br><br></a><p>103 </p><a href="/103-09-22">https://telegra.ph/103-09-22 <br><br></a><p>110 </p><a href="/110-09-22">https://telegra.ph/110-09-22 <br><br></a><p>116</p><a href="/116-09-22"> https://telegra.ph/116-09-22 <br><br></a><a href="https://t.me/sese_MoMo" target="_blank">更多内容请访问频道 @sese_MoMo</a></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF%20%E7%9B%AE%E5%BD%95%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2F%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E7%9B%AE%E5%BD%95-09-24%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1664002318,"pageId":"ff3d9f415ce532aa1da8b","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=%E5%8C%97%E4%BA%AC%E5%A4%A9%E4%BD%BF-%E7%9B%AE%E5%BD%95-09-24&hash=a908d2638cef7dfdd9"></script>
                                https://telegra.ph/file/ab3bd78f6e362f9fa8264.png
                              </body>
                            </html>
                    """;

    private static final String HTML_URI_BODY =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>Алина – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="Алина">
                                <meta property="og:description" content="ПОЛНЫЙ ДОСТУП с ней уже в нашем Приватном Боте🤖
                            Узнать подробное описание тарифов и приобрести доступ можно ЗДЕСЬ: http://t.me/bot_aezakmi_bot
                                        
                            При покупке слива через ПРИВАТНОГО БОТА вы получите:
                             - ВИДЕО и ФОТО девушек без цензуры
                             - Развод девушек через Skype звонки
                             - Интим, ссылки и переписки(при наличии)
                            \s
                             👇🏻ПОЛУЧИТЬ ДОСТУП ЗДЕСЬ👇🏻\s
                            http://t.me/bot_aezakmi_bot">
                                <meta property="og:image" content="https://telegra.ph/file/ab3bd78f6e362f9fa8264.png">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2022-08-09T16:40:15+0000">
                                <meta property="article:modified_time" content="2022-08-09T16:40:15+0000">
                                <meta property="article:author" content="">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="Алина">
                                <meta name="twitter:description" content="ПОЛНЫЙ ДОСТУП с ней уже в нашем Приватном Боте🤖
                            Узнать подробное описание тарифов и приобрести доступ можно ЗДЕСЬ: http://t.me/bot_aezakmi_bot
                                        
                            При покупке слива через ПРИВАТНОГО БОТА вы получите:
                             - ВИДЕО и ФОТО девушек без цензуры
                             - Развод девушек через Skype звонки
                             - Интим, ссылки и переписки(при наличии)
                            \s
                             👇🏻ПОЛУЧИТЬ ДОСТУП ЗДЕСЬ👇🏻\s
                            http://t.me/bot_aezakmi_bot">
                                <meta name="twitter:image" content="https://telegra.ph/file/ab3bd78f6e362f9fa8264.png">
                                <link rel="canonical" href="https://telegra.ph/Alina-08-09-14" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>Алина</h1>
                                        <address>
                                          <a rel="author"></a><!--
                                       --><time datetime="2022-08-09T16:40:15+0000">August 09, 2022</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>Алина<br></h1><address><br></address><figure><img src="/file/ab3bd78f6e362f9fa8264.png"><figcaption></figcaption></figure><figure><img src="file/048e2fe8864fa7a473de0.png"><figcaption></figcaption></figure><p><em>ПОЛНЫЙ ДОСТУП с ней уже в нашем </em><a href="http://t.me/bot_aezakmi_bot" target="_blank"><em>Приватном Боте</em></a><em>🤖</em></p><p><strong>Узнать подробное описание тарифов и приобрести доступ можно ЗДЕСЬ: </strong><a href="http://t.me/bot_aezakmi_bot" target="_blank"><strong>http://t.me/bot_aezakmi_bot</strong></a></p><figure><img src="/file/8cf022f2b26cf41ae6005.png"><figcaption></figcaption></figure><figure><img src="/file/f95dd7dab3691c3836b19.png"><figcaption></figcaption></figure><figure><img src="/file/79db0ce5193ebefc11f43.png"><figcaption></figcaption></figure><figure><img src="/file/b495c5fd2d669bca054ff.png"><figcaption></figcaption></figure><figure><img src="/file/9905becde5c4149bdf94e.png"><figcaption></figcaption></figure><figure><img src="/file/fe0ab5405e8577d6a2384.png"><figcaption></figcaption></figure><figure><img src="/file/5e71be196fbcab2d0ffaf.png"><figcaption></figcaption></figure><figure><img src="/file/73b4d576f7e2116a05d39.png"><figcaption></figcaption></figure><figure><img src="/file/1766b4c29c6326645bbb0.png"><figcaption></figcaption></figure><figure><img src="/file/9e4f328bf2baa593c9ecb.png"><figcaption></figcaption></figure><figure><img src="/file/c51780f8d634a37d12dea.png"><figcaption></figcaption></figure><figure><img src="/file/b814efb3a8bae48671667.png"><figcaption></figcaption></figure><figure><img src="/file/d787f1a910ab81df295a8.png"><figcaption></figcaption></figure><figure><img src="/file/f0beebc570bb01baeb44c.png"><figcaption></figcaption></figure><p><br></p><p><strong>При покупке слива через </strong><a href="http://t.me/bot_aezakmi_bot" target="_blank"><strong>ПРИВАТНОГО БОТА </strong></a><strong>вы получите:</strong></p><p><strong> - ВИДЕО и ФОТО девушек без цензуры</strong></p><p><strong> - Развод девушек через Skype звонки</strong></p><p><strong> - Интим, ссылки и переписки(при наличии)</strong></p><p><strong> </strong></p><p><strong> 👇🏻ПОЛУЧИТЬ ДОСТУП ЗДЕСЬ👇🏻 </strong></p><p><a href="http://t.me/bot_aezakmi_bot" target="_blank"><strong>http://t.me/bot_aezakmi_bot</strong></a></p></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22%D0%90%D0%BB%D0%B8%D0%BD%D0%B0%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2FAlina-08-09-14%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1660063215,"pageId":"805d2e220cb4167f91f46","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=alina-08-09-14&hash=6adc551de96bf831ee"></script>
                              </body>
                            </html>
                    """;

    private static final String EXTERNAL_DISKS_BODY_MAIL_RU =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>Роксана – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="Роксана">
                                <meta property="og:description" content="Видео: https://cloud.mail.ru/public/3kxS/3hADQ2FDF(если видео не доступно, обращаться к админу сообщества)Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA">
                                <meta property="og:image" content="https://telegra.ph/file/616c3d2d736684c8aeeef.png">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2020-07-23T14:41:46+0000">
                                <meta property="article:modified_time" content="2020-07-23T14:41:46+0000">
                                <meta property="article:author" content="Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="Роксана">
                                <meta name="twitter:description" content="Видео: https://cloud.mail.ru/public/3kxS/3hADQ2FDF(если видео не доступно, обращаться к админу сообщества)Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA">
                                <meta name="twitter:image" content="https://telegra.ph/file/616c3d2d736684c8aeeef.png">
                                <link rel="canonical" href="https://telegra.ph/Roksana-07-23" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>Роксана</h1>
                                        <address>
                                          <a rel="author" href="https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA" target="_blank">Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA</a><!--
                                       --><time datetime="2020-07-23T14:41:46+0000">July 23, 2020</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>Роксана<br></h1><address><a href="https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA" target="_blank">Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA</a><br></address><figure><img src="/file/616c3d2d736684c8aeeef.png"><figcaption></figcaption></figure><figure><img src="/file/bb150a65db2bc19ea78f5.png"><figcaption></figcaption></figure><figure><img src="/file/01cb63bf575cdb3b3c47f.png"><figcaption></figcaption></figure><figure><img src="/file/e03bd78a96c66a7e15c4a.png"><figcaption></figcaption></figure><figure><img src="/file/a378bee4fbe651a8631f9.png"><figcaption></figcaption></figure><figure><img src="/file/0d7c099e9f7e419fd2532.png"><figcaption></figcaption></figure><figure><img src="/file/251546982a18601793c33.png"><figcaption></figcaption></figure><figure><img src="/file/5a609690152ef8bf62279.png"><figcaption></figcaption></figure><figure><img src="/file/94f671d30ec684bbbdf97.png"><figcaption></figcaption></figure><figure><img src="/file/0148ff93a088af7d7fc22.png"><figcaption></figcaption></figure><figure><img src="/file/c64cd47c032cac2a24f0e.png"><figcaption></figcaption></figure><figure><img src="/file/4896661eccc1d7f3ad0bb.png"><figcaption></figcaption></figure><figure><img src="/file/239d0f888e51f166b6193.png"><figcaption></figcaption></figure><figure><img src="/file/d48bcb6656c67e5184d75.png"><figcaption></figcaption></figure><figure><img src="/file/8e5ccd88d9c47361d69f2.png"><figcaption></figcaption></figure><figure><img src="/file/8ba77ef16289b27953cf5.png"><figcaption></figcaption></figure><figure><img src="/file/6faa29821f58d84df8df6.png"><figcaption></figcaption></figure><figure><img src="/file/8bcf3ae809f34a5c9904a.png"><figcaption></figcaption></figure><figure><img src="/file/8526781dffccfed82e3fb.png"><figcaption></figcaption></figure><figure><img src="/file/e11b29c19f0ebfd186d14.png"><figcaption></figcaption></figure><p>Видео: <a href="https://cloud.mail.ru/public/3kxS/3hADQ2FDF" target="_blank">https://cloud.mail.ru/public/3kxS/3hADQ2FDF</a><br class="inline">(если видео не доступно, обращаться к админу сообщества)<br class="inline"><br class="inline">Наш телеграмм: <a href="https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA" target="_blank">https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA</a></p></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22%D0%A0%D0%BE%D0%BA%D1%81%D0%B0%D0%BD%D0%B0%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2FRoksana-07-23%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1595515306,"pageId":"a62a1d09c3bf13239df47","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=roksana-07-23&hash=01d31117eafaf592db"></script>
                              </body>
                            </html>
                                
                    """;

    private static final String EXTERNAL_DISKS_BODY_YANDEX =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>Леся – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="Леся">
                                <meta property="og:description" content="видео -&gt; https://yadi.sk/d/grNPlsO1BY6phg видео -&gt; https://yadi.sk/d/Jjxv5G4zELtPZQ">
                                <meta property="og:image" content="">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2020-08-07T00:54:26+0000">
                                <meta property="article:modified_time" content="2020-08-07T00:54:26+0000">
                                <meta property="article:author" content="ЧЖ (18+)">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="Леся">
                                <meta name="twitter:description" content="видео -&gt; https://yadi.sk/d/grNPlsO1BY6phg видео -&gt; https://yadi.sk/d/Jjxv5G4zELtPZQ">
                                <meta name="twitter:image" content="">
                                <link rel="canonical" href="https://telegra.ph/Lesya-08-07" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>Леся</h1>
                                        <address>
                                          <a rel="author">ЧЖ (18+)</a><!--
                                       --><time datetime="2020-08-07T00:54:26+0000">August 07, 2020</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>Леся<br></h1><address>ЧЖ (18+)<br></address><p><br></p><p>видео -&gt; <a href="https://yadi.sk/d/grNPlsO1BY6phg" target="_blank">https://yadi.sk/d/grNPlsO1BY6phg </a><br class="inline">видео -&gt; <a href="https://yadi.sk/d/Jjxv5G4zELtPZQ" target="_blank">https://yadi.sk/d/Jjxv5G4zELtPZQ</a><br class="inline"></p><figure><img src="/file/d6accf2320c94c19cd6e1.png"><figcaption></figcaption></figure><p><br></p><figure><img src="/file/a49a29bccfb25b8c369ae.png"><figcaption></figcaption></figure><figure><img src="/file/30d219c3969c8125631d6.png"><figcaption></figcaption></figure><figure><img src="/file/b300f5262b8a6af359c9e.png"><figcaption></figcaption></figure><figure><img src="/file/0f3268710686e22e621ee.png"><figcaption></figcaption></figure><figure><img src="/file/cb96ae3118ab3496997b2.png"><figcaption></figcaption></figure><figure><img src="/file/6565d19483f8aba8c2cbe.png"><figcaption></figcaption></figure><figure><img src="/file/834707b6d13738ab753e1.png"><figcaption></figcaption></figure><figure><img src="/file/67fc6a8358a1f652f9987.jpg"><figcaption></figcaption></figure><figure><img src="/file/baf9c4169118b5746830f.jpg"><figcaption></figcaption></figure><figure><img src="/file/ce50fa550117b8c4458ef.png"><figcaption></figcaption></figure><figure><img src="/file/f922e6dea2542ad95f251.png"><figcaption></figcaption></figure><figure><img src="/file/cc7b3cb0ff689f67c3c36.png"><figcaption></figcaption></figure><figure><img src="/file/5e3c2e13487190c758637.jpg"><figcaption></figcaption></figure><figure><img src="/file/abcdb34fd312bbad16d4f.jpg"><figcaption></figcaption></figure><figure><img src="/file/671a976aa3d451ee37678.jpg"><figcaption></figcaption></figure><figure><img src="/file/c80f11b0aa2223cf5dfbd.jpg"><figcaption></figcaption></figure><figure><img src="/file/6d985848ac8d395bf7376.jpg"><figcaption></figcaption></figure><figure><img src="/file/6cfe4ac5d4154e59564e4.jpg"><figcaption></figcaption></figure><figure><img src="/file/4b6cd77c8f9e1f064e98e.jpg"><figcaption></figcaption></figure><figure><img src="/file/3572889ae853da333dbb2.jpg"><figcaption></figcaption></figure><figure><img src="/file/e5df2aeafba23fd084366.jpg"><figcaption></figcaption></figure><figure><img src="/file/4f6b47d0df7307576a81d.jpg"><figcaption></figcaption></figure><figure><img src="/file/630bdf95f1f20ee00f03b.jpg"><figcaption></figcaption></figure><figure><img src="/file/e1d4a136ac2db77f6654c.jpg"><figcaption></figcaption></figure><figure><img src="/file/2c584a6a61942d851fa28.jpg"><figcaption></figcaption></figure><figure><img src="/file/b1a3f271206531b03e21f.jpg"><figcaption></figcaption></figure><figure><img src="/file/9a4bba7d5ce4bc9ae4c82.jpg"><figcaption></figcaption></figure><figure><img src="/file/64d86e5ef9ddfcaf325c1.jpg"><figcaption></figcaption></figure><figure><img src="/file/00af8e94b12fcbcb6b0fa.jpg"><figcaption></figcaption></figure><figure><img src="/file/1cff2f2d3ba64f19e4d9c.jpg"><figcaption></figcaption></figure><figure><img src="/file/dab17dcdab41bab6886f8.jpg"><figcaption></figcaption></figure><figure><img src="/file/b9dc34345ee4a83cfdebf.jpg"><figcaption></figcaption></figure><p><br></p></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22%D0%9B%D0%B5%D1%81%D1%8F%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2FLesya-08-07%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1596761666,"pageId":"49e2f87ff54689225675c","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=lesya-08-07&hash=09609fbaa20e56b1f1"></script>
                              </body>
                            </html>
                    """;
    private final static String EXTERNAL_DISKS_BODY_GOOGLE =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>Анжелика – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="Анжелика">
                                <meta property="og:description" content="Для просмотра видео с данной особой перейдите по ссылке на google диск
                            1)https://drive.google.com/file/d/1FZylIZ4dW2L9aVmAkqb55j5XVXHYtutH/view?usp=drivesdk
                            2)https://drive.google.com/file/d/18Rc-b2xNF89h-mHd_tk8euTyefW1FqbB/view?usp=drivesdk
                            3)https://drive.google.com/file/d/1lN5XauZy6-jqeH0vOnLL0VrEYjs22Kb0/view?usp=drivesdk
                            4)https://drive.google.com/file/d/1kBDlbbAHEWzPTk3ris3Y6TXWONPReRsr/view?usp=drivesdk">
                                <meta property="og:image" content="https://telegra.ph/file/3cf9dd16fa4346621214e.jpg">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2019-09-23T17:00:59+0000">
                                <meta property="article:modified_time" content="2019-09-23T17:02:09+0000">
                                <meta property="article:author" content="Сливач">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="Анжелика">
                                <meta name="twitter:description" content="Для просмотра видео с данной особой перейдите по ссылке на google диск
                            1)https://drive.google.com/file/d/1FZylIZ4dW2L9aVmAkqb55j5XVXHYtutH/view?usp=drivesdk
                            2)https://drive.google.com/file/d/18Rc-b2xNF89h-mHd_tk8euTyefW1FqbB/view?usp=drivesdk
                            3)https://drive.google.com/file/d/1lN5XauZy6-jqeH0vOnLL0VrEYjs22Kb0/view?usp=drivesdk
                            4)https://drive.google.com/file/d/1kBDlbbAHEWzPTk3ris3Y6TXWONPReRsr/view?usp=drivesdk">
                                <meta name="twitter:image" content="https://telegra.ph/file/3cf9dd16fa4346621214e.jpg">
                                <link rel="canonical" href="https://telegra.ph/Anzhelika-09-23" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>Анжелика</h1>
                                        <address>
                                          <a rel="author">Сливач</a><!--
                                       --><time datetime="2019-09-23T17:00:59+0000">September 23, 2019</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>Анжелика<br></h1><address>Сливач<br></address><p><br></p><figure><img src="/file/3cf9dd16fa4346621214e.jpg"><figcaption></figcaption></figure><figure><img src="/file/5e6bbf4e67217a6df40fa.jpg"><figcaption></figcaption></figure><figure><img src="/file/0935ade4cb8972a282112.jpg"><figcaption></figcaption></figure><figure><img src="/file/3fcbd12d66567f2d0fafe.jpg"><figcaption></figcaption></figure><figure><img src="/file/85c54bb380b9d49f1f859.jpg"><figcaption></figcaption></figure><figure><img src="/file/d03b5dc38058fb9b2b383.jpg"><figcaption></figcaption></figure><figure><img src="/file/28b0e88df11f10071c80c.jpg"><figcaption></figcaption></figure><figure><img src="/file/15a6e12b274678e7c4ace.jpg"><figcaption></figcaption></figure><figure><img src="/file/2f5990d51bb62d92f5461.jpg"><figcaption></figcaption></figure><figure><img src="/file/24deee84453856e090e68.jpg"><figcaption></figcaption></figure><p>Для просмотра видео с данной особой перейдите по ссылке на google диск</p><p>1)<a href="https://drive.google.com/file/d/1FZylIZ4dW2L9aVmAkqb55j5XVXHYtutH/view?usp=drivesdk" target="_blank">https://drive.google.com/file/d/1FZylIZ4dW2L9aVmAkqb55j5XVXHYtutH/view?usp=drivesdk</a></p><p>2)<a href="https://drive.google.com/file/d/18Rc-b2xNF89h-mHd_tk8euTyefW1FqbB/view?usp=drivesdk" target="_blank">https://drive.google.com/file/d/18Rc-b2xNF89h-mHd_tk8euTyefW1FqbB/view?usp=drivesdk</a></p><p>3)<a href="https://drive.google.com/file/d/1lN5XauZy6-jqeH0vOnLL0VrEYjs22Kb0/view?usp=drivesdk" target="_blank">https://drive.google.com/file/d/1lN5XauZy6-jqeH0vOnLL0VrEYjs22Kb0/view?usp=drivesdk</a></p><p>4)<a href="https://drive.google.com/file/d/1kBDlbbAHEWzPTk3ris3Y6TXWONPReRsr/view?usp=drivesdk" target="_blank">https://drive.google.com/file/d/1kBDlbbAHEWzPTk3ris3Y6TXWONPReRsr/view?usp=drivesdk</a></p></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22%D0%90%D0%BD%D0%B6%D0%B5%D0%BB%D0%B8%D0%BA%D0%B0%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2FAnzhelika-09-23%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1569258059,"pageId":"0c67f65fe377a52fcd6f8","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=anzhelika-09-23&hash=24165c4435df45ab91"></script>
                              </body>
                            </html>
                    """;
    private static final String SOCIAL_NETWORKS_BODY =
            """
                    vk.com/user",
                    ok.ru/user",
                    fb.com/user",
                    "facebook.com/user",
                    "instagram.com/user"
                    """;
    private static final String TELEGRAM_BODY =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>Роксана – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="Роксана">
                                <meta property="og:description" content="Видео: https://cloud.mail.ru/public/3kxS/3hADQ2FDF(если видео не доступно, обращаться к админу сообщества)Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA">
                                <meta property="og:image" content="https://telegra.ph/file/616c3d2d736684c8aeeef.png">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2020-07-23T14:41:46+0000">
                                <meta property="article:modified_time" content="2020-07-23T14:41:46+0000">
                                <meta property="article:author" content="Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="Роксана">
                                <meta name="twitter:description" content="Видео: https://cloud.mail.ru/public/3kxS/3hADQ2FDF(если видео не доступно, обращаться к админу сообщества)Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA">
                                <meta name="twitter:image" content="https://telegra.ph/file/616c3d2d736684c8aeeef.png">
                                <link rel="canonical" href="https://telegra.ph/Roksana-07-23" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>Роксана</h1>
                                        <address>
                                          <a rel="author" href="https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA" target="_blank">Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA</a><!--
                                       --><time datetime="2020-07-23T14:41:46+0000">July 23, 2020</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>Роксана<br></h1><address><a href="https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA" target="_blank">Наш телеграмм: https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA</a><br></address><figure><img src="/file/616c3d2d736684c8aeeef.png"><figcaption></figcaption></figure><figure><img src="/file/bb150a65db2bc19ea78f5.png"><figcaption></figcaption></figure><figure><img src="/file/01cb63bf575cdb3b3c47f.png"><figcaption></figcaption></figure><figure><img src="/file/e03bd78a96c66a7e15c4a.png"><figcaption></figcaption></figure><figure><img src="/file/a378bee4fbe651a8631f9.png"><figcaption></figcaption></figure><figure><img src="/file/0d7c099e9f7e419fd2532.png"><figcaption></figcaption></figure><figure><img src="/file/251546982a18601793c33.png"><figcaption></figcaption></figure><figure><img src="/file/5a609690152ef8bf62279.png"><figcaption></figcaption></figure><figure><img src="/file/94f671d30ec684bbbdf97.png"><figcaption></figcaption></figure><figure><img src="/file/0148ff93a088af7d7fc22.png"><figcaption></figcaption></figure><figure><img src="/file/c64cd47c032cac2a24f0e.png"><figcaption></figcaption></figure><figure><img src="/file/4896661eccc1d7f3ad0bb.png"><figcaption></figcaption></figure><figure><img src="/file/239d0f888e51f166b6193.png"><figcaption></figcaption></figure><figure><img src="/file/d48bcb6656c67e5184d75.png"><figcaption></figcaption></figure><figure><img src="/file/8e5ccd88d9c47361d69f2.png"><figcaption></figcaption></figure><figure><img src="/file/8ba77ef16289b27953cf5.png"><figcaption></figcaption></figure><figure><img src="/file/6faa29821f58d84df8df6.png"><figcaption></figcaption></figure><figure><img src="/file/8bcf3ae809f34a5c9904a.png"><figcaption></figcaption></figure><figure><img src="/file/8526781dffccfed82e3fb.png"><figcaption></figcaption></figure><figure><img src="/file/e11b29c19f0ebfd186d14.png"><figcaption></figcaption></figure><p>Видео: <a href="https://cloud.mail.ru/public/3kxS/3hADQ2FDF" target="_blank">https://cloud.mail.ru/public/3kxS/3hADQ2FDF</a><br class="inline">(если видео не доступно, обращаться к админу сообщества)<br class="inline"><br class="inline">Наш телеграмм: <a href="https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA" target="_blank">https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA</a></p></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22%D0%A0%D0%BE%D0%BA%D1%81%D0%B0%D0%BD%D0%B0%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2FRoksana-07-23%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1595515306,"pageId":"a62a1d09c3bf13239df47","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=roksana-07-23&hash=01d31117eafaf592db"></script>
                              </body>
                            </html>
                    """;
    private final static String IP_BODY =
            """
                            <!DOCTYPE html>
                            <html>
                              <head>
                                <meta charset="utf-8">
                                <title>SSH – Telegraph</title>
                                <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
                                <meta name="format-detection" content="telephone=no" />
                                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                                <meta name="MobileOptimized" content="176" />
                                <meta name="HandheldFriendly" content="True" />
                                <meta name="robots" content="index, follow" />
                                <meta property="og:type" content="article">
                                <meta property="og:title" content="SSH">
                                <meta property="og:description" content="Country State City Ip Port Username Password
                            GB Nottinghamshire Kettering 87.242.208.2 22 guest guest
                            GB Windsor and Maidenhead Maidenhead 78.31.110.65 22222 ftp ftp
                            GB London City of London 5.148.33.21 22 monitor monitor
                            GB Lancashire Morecambe 2.28.128.236 22 backup password
                            GB Bromley Orpington 109.204.44.230 2222 user live
                            GB Manchester Manchester 79.78.138.99 24 user 1234
                            GB Swindon Swindon 149.255.56.68 22 listd 54e172662
                            GB Bristol City of Bristol 188.39.98.66 6022 cooper cooper
                            GB London City of London…">
                                <meta property="og:image" content="">
                                <meta property="og:site_name" content="Telegraph">
                                <meta property="article:published_time" content="2018-01-28T18:44:38+0000">
                                <meta property="article:modified_time" content="2018-01-28T18:44:38+0000">
                                <meta property="article:author" content="">
                                <meta name="twitter:card" content="summary">
                                <meta name="twitter:title" content="SSH">
                                <meta name="twitter:description" content="Country State City Ip Port Username Password
                            GB Nottinghamshire Kettering 87.242.208.2 22 guest guest
                            GB Windsor and Maidenhead Maidenhead 78.31.110.65 22222 ftp ftp
                            GB London City of London 5.148.33.21 22 monitor monitor
                            GB Lancashire Morecambe 2.28.128.236 22 backup password
                            GB Bromley Orpington 109.204.44.230 2222 user live
                            GB Manchester Manchester 79.78.138.99 24 user 1234
                            GB Swindon Swindon 149.255.56.68 22 listd 54e172662
                            GB Bristol City of Bristol 188.39.98.66 6022 cooper cooper
                            GB London City of London…">
                                <meta name="twitter:image" content="">
                                <link rel="canonical" href="https://telegra.ph/SSH-01-28" />
                                <link rel="shortcut icon" href="/favicon.ico?1" type="image/x-icon">
                                <link rel="icon" type="image/png" href="/images/favicon.png?1" sizes="16x16">
                                <link rel="icon" type="image/png" href="/images/favicon_2x.png?1" sizes="32x32">
                                <link href="/css/quill.core.min.css" rel="stylesheet">
                                <link href="/css/core.min.css?47" rel="stylesheet">
                              </head>
                              <body>
                                <div class="tl_page_wrap">
                                  <div class="tl_page">
                                    <main class="tl_article">
                                      <header class="tl_article_header" dir="auto">
                                        <h1>SSH</h1>
                                        <address>
                                          <a rel="author"></a><!--
                                       --><time datetime="2018-01-28T18:44:38+0000">January 28, 2018</time>
                                        </address>
                                      </header>
                                      <article id="_tl_editor" class="tl_article_content"><h1>SSH<br></h1><address><br></address><p>Country State City Ip Port Username Password</p><p>GB Nottinghamshire Kettering 87.242.208.2 22 guest guest</p><p>GB Windsor and Maidenhead Maidenhead 78.31.110.65 22222 ftp ftp</p><p>GB London City of London 5.148.33.21 22 monitor monitor</p><p>GB Lancashire Morecambe 2.28.128.236 22 backup password</p><p>GB Bromley Orpington 109.204.44.230 2222 user live</p><p>GB Manchester Manchester 79.78.138.99 24 user 1234</p><p>GB Swindon Swindon 149.255.56.68 22 listd 54e172662</p><p>GB Bristol City of Bristol 188.39.98.66 6022 cooper cooper</p><p>GB London City of London 138.68.141.97 22 support support</p><p>GB Greenwich Greenwich 188.172.159.250 22 support password</p><p>DE Berlin Berlin 151.252.42.136 22 root b</p><p>DE Hessen Frankfurt 207.154.217.224 22 squid 123456</p><p>DE Baden-Wurttemberg Leimen 213.23.121.189 22 a a</p><p>DE Nordrhein-Westfalen Aachen 37.201.138.84 22 listd 54e172662</p><p>DE Bremen Bremen 77.22.243.112 22 mysql 123456</p><p>DE Hessen Frankfurt 31.186.250.187 22 hadoop hadoop</p><p>DE Nordrhein-Westfalen Hst 83.169.23.231 22 capitaly 123qwe</p><p>DE Sachsen Taucha 85.232.13.17 22 testuser testuser</p><p>DE Baden-Wurttemberg Hechingen 20022 guest guest</p><p>DE Thuringen Bad Langensalza 31.3.84.22 2209 ubnt ubnt</p><p>DE Baden-Wurttemberg Furtwangen 141.28.73.189 22 listd 54e172662</p><p>DE Nordrhein-Westfalen Hst 62.138.1.215 22 mongodb mongodb</p><p>DE Bayern Wunsiedel 185.41.110.57 4022 listd 54e172662</p><p>DE Niedersachsen Metjendorf 134.106.116.190 22 ben ben</p><p>DE Nordrhein-Westfalen Bonn 37.201.114.112 22 root libreelec</p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p> </p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p><p><br></p></article>
                                      <div id="_tl_link_tooltip" class="tl_link_tooltip"></div>
                                      <div id="_tl_tooltip" class="tl_tooltip">
                                        <div class="buttons">
                                          <span class="button_hover"></span>
                                          <span class="button_group"><!--
                                         --><button id="_bold_button"></button><!--
                                         --><button id="_italic_button"></button><!--
                                         --><button id="_link_button"></button><!--
                                       --></span><!--
                                       --><span class="button_group"><!--
                                         --><button id="_header_button"></button><!--
                                         --><button id="_subheader_button"></button><!--
                                         --><button id="_quote_button"></button><!--
                                       --></span>
                                        </div>
                                        <div class="prompt">
                                          <span class="close"></span>
                                          <div class="prompt_input_wrap"><input type="url" class="prompt_input" /></div>
                                        </div>
                                      </div>
                                      <div id="_tl_blocks" class="tl_blocks">
                                        <div class="buttons">
                                          <button id="_image_button"></button><!--
                                       --><button id="_embed_button"></button>
                                        </div>
                                      </div>
                                      <aside class="tl_article_buttons">
                                        <div class="account account_top"></div>
                                        <button id="_edit_button" class="button edit_button">Edit</button><!--
                                     --><button id="_publish_button" class="button publish_button">Publish</button>
                                        <div class="account account_bottom"></div>
                                        <div id="_error_msg" class="error_msg"></div>
                                      </aside>
                                    </main>
                                  </div>
                                      <div class="tl_page_footer">
                                  <div id="_report_button" class="tl_footer_button">Report content on this page</div>
                                </div>
                                </div>
                                  <div class="tl_popup tl_popup_hidden" id="_report_popup">
                                <main class="tl_popup_body tl_report_popup">
                                  <form id="_report_form" method="post">
                                    <section>
                                      <h2 class="tl_popup_header">Report Page</h2>
                                      <div class="tl_radio_items">
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="violence">
                                          <span class="tl_radio_item_label">Violence</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="childabuse">
                                          <span class="tl_radio_item_label">Child Abuse</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="copyright">
                                          <span class="tl_radio_item_label">Copyright</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="illegal_drugs">
                                          <span class="tl_radio_item_label">Illegal Drugs</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="personal_details">
                                          <span class="tl_radio_item_label">Personal Details</span>
                                        </label>
                                        <label class="tl_radio_item">
                                          <input type="radio" class="radio" name="reason" value="other">
                                          <span class="tl_radio_item_label">Other</span>
                                        </label>
                                      </div>
                                      <div class="tl_textfield_item tl_comment_field">
                                        <input type="text" class="tl_textfield" name="comment" value="" placeholder="Add Comment…">
                                      </div>
                                      <div class="tl_copyright_field">
                                        Please submit your DMCA takedown request to <a href="mailto:dmca@telegram.org?subject=Report%20to%20Telegraph%20page%20%22SSH%22&body=Reported%20page%3A%20https%3A%2F%2Ftelegra.ph%2FSSH-01-28%0A%0A%0A" target="_blank">dmca@telegram.org</a>
                                      </div>
                                    </section>
                                    <aside class="tl_popup_buttons">
                                      <button type="reset" class="button" id="_report_cancel">Cancel</button>
                                      <button type="submit" class="button submit_button">Report</button>
                                    </aside>
                                  </form>
                                </main>
                              </div>
                               \s
                                <script>var T={"apiUrl":"https:\\/\\/edit.telegra.ph","datetime":1517165078,"pageId":"008a62a77a879449debb2","editable":true};(function(){var b=document.querySelector('time');if(b&&T.datetime){var a=new Date(1E3*T.datetime),d='January February March April May June July August September October November December'.split(' ')[a.getMonth()],c=a.getDate();b.innerText=d+' '+(10>c?'0':'')+c+', '+a.getFullYear()}})();</script>
                                <script src="/js/jquery.min.js"></script>
                                <script src="/js/jquery.selection.min.js"></script>
                                <script src="/js/autosize.min.js"></script>
                                <script src="/js/load-image.all.min.js?1"></script>
                                <script src="/js/quill.min.js?9"></script>
                                <script src="/js/core.min.js?65"></script>
                                <script async src="https://t.me/_websync_?path=ssh-01-28&hash=0ed6952c76ac9600fe"></script>
                              </body>
                            </html>
                    """;
    private final static String URL_BODY =
            """
                    https://a.sg.servergo.pw
                    http://a.sg.servergo.pw
                    ftp://a.sg.servergo.pw
                    psql://a.sg.servergo.pw
                    """;


    @Test
    public void findTelegraphUrlsTest() {
        Set<String> foundTelegraphUrls = Analyzer.findTelegraphUrls(TELEGRAPH_URL_BODY);
        Assertions.assertEquals(34, foundTelegraphUrls.size(), "Количество найденных ссылок");
    }

    @Test
    public void findHtmlUrisTest() {
        Set<String> foundHtmlUris = Analyzer.findHtmlUris(HTML_URI_BODY);
        Assertions.assertEquals(16, foundHtmlUris.size(), "Количество найденных ссылок в html");
    }

    @Test
    public void findExternalDisksMailRuTest() {
        Set<String> externalDisks = Analyzer.findExternalDisks(EXTERNAL_DISKS_BODY_MAIL_RU);
        Assertions.assertEquals(
                Set.of("https://cloud.mail.ru/public/3kxS/3hADQ2FDF"),
                externalDisks,
                "Найденные mail ru ссылки"
        );
    }

    @Test
    public void findExternalDisksYandexTest() {
        Set<String> externalDisks = Analyzer.findExternalDisks(EXTERNAL_DISKS_BODY_YANDEX);
        Assertions.assertEquals(
                Set.of("https://yadi.sk/d/grNPlsO1BY6phg", "https://yadi.sk/d/Jjxv5G4zELtPZQ"),
                externalDisks,
                "Найденные yandex ссылки"
        );
    }

    @Test
    public void findExternalDisksGoogleTest() {
        Set<String> externalDisks = Analyzer.findExternalDisks(EXTERNAL_DISKS_BODY_GOOGLE);
        Assertions.assertEquals(
                Set.of(
                        "https://drive.google.com/file/d/1lN5XauZy6-jqeH0vOnLL0VrEYjs22Kb0/view?usp=drivesdk",
                        "https://drive.google.com/file/d/1FZylIZ4dW2L9aVmAkqb55j5XVXHYtutH/view?usp=drivesdk",
                        "https://drive.google.com/file/d/1kBDlbbAHEWzPTk3ris3Y6TXWONPReRsr/view?usp=drivesdk",
                        "https://drive.google.com/file/d/18Rc-b2xNF89h-mHd_tk8euTyefW1FqbB/view?usp=drivesdk"
                ),
                externalDisks,
                "Найденные google drive ссылки"
        );
    }

    @Test
    public void findSocialNetworksTest() {
        Set<String> socialNetworks = Analyzer.findSocialNetworks(SOCIAL_NETWORKS_BODY);
        Assertions.assertEquals(
                Set.of(
                        "https://ok.ru/user",
                        "https://vk.com/user",
                        "https://facebook.com/user",
                        "https://fb.com/user",
                        "https://instagram.com/user"
                ),
                socialNetworks,
                "Найденные соц сети"
        );
    }

    @Test
    public void findTelegramLinksTest() {
        Set<String> telegramLinks = Analyzer.findTelegramLinks(TELEGRAM_BODY);
        Assertions.assertEquals(
                Set.of(
                        "https://t.me/joinchat/AAAAAEW1W_VR2BZbxMJIZA"
                ),
                telegramLinks,
                "Найденные ссылки telegram"
        );
    }

    @Test
    public void findIpsTest() {
        Set<String> ips = Analyzer.findIps(IP_BODY);
        Assertions.assertEquals(
                Set.of(
                        "138.68.141.97",
                        "207.154.217.224",
                        "62.138.1.215",
                        "188.172.159.250",
                        "78.31.110.65",
                        "85.232.13.17",
                        "87.242.208.2",
                        "149.255.56.68",
                        "83.169.23.231",
                        "109.204.44.230",
                        "2.28.128.236",
                        "31.186.250.187",
                        "185.41.110.57",
                        "37.201.138.84",
                        "37.201.114.112",
                        "141.28.73.189",
                        "134.106.116.190",
                        "77.22.243.112",
                        "79.78.138.99",
                        "188.39.98.66",
                        "213.23.121.189",
                        "31.3.84.22",
                        "5.148.33.21",
                        "151.252.42.136"
                ),
                ips,
                "Найденные ip адреса"
        );
    }

    @Test
    public void findUrlsTest() {
        Set<String> urls = Analyzer.findUrls(URL_BODY);
        Assertions.assertEquals(
                Set.of(
                        "ftp://a.sg.servergo.pw",
                        "http://a.sg.servergo.pw",
                        "psql://a.sg.servergo.pw",
                        "https://a.sg.servergo.pw"
                ),
                urls,
                "Найденные внешние ссылки"
        );
    }

    @Test
    public void parseSavedAtTest() {
        String saved = Analyzer.parseSavedAt(TELEGRAM_BODY);
        Assertions.assertEquals("2020-07-23T14:41:46+0000", saved, "Получаем дату создания");
    }
}
