# Vagabond

Система индексации статей telegra.ph.

## Структура

1. `src/` - код приложения
2. `uri_init/` - скрипты, которые использовались для генерации uri-ев для запуска цикла индексации

## Принцип работы

Основная логика приложения реализована на Executor-ах, которые запускаются в отдельных потоках на старте приложения.

На данный момент существует 5 executor-ов.

1. CrawlerExecutor - по uri ищет существующие ссылки в telegra.ph, может работать в несколько потоков
2. IndexerExecutor - проверяет уже найденные url-ы telegra.ph, вытаскивая из статей информацию и ссылки, может работать в несколько потоков
3. TransactionalExecutor - исполняет транзакции записи в базу данных, которые ему передают crawler и indexer, работает в несколько потоков
4. UnlockExecutor - раз в несколько минут снимает локи с uri и url в базе данных, которые уже существуют больше определенного количества минут
5. CommandExecutor - выполняет команды, которые записаны в `infra.command`

## Requirements

1. JRE >= 21
2. Postgresql > 16

## How-To development environment

1. Развертываем бд
2. `make docker-run` - сборка jar, создание docker образа и запуск

## Infra environment

В приложении существует таблица `infra.environment` в которой содержатся конфиги, которые могут меняться в процессе работы приложения.

## Environment variables

1. `HOSTNAME` - `default: main` переменная, отвечающая за название текущего хоста
2. `ADDRESS` - `default: 127.0.0.1` адрес, который прослушивает приложение
3. `PORT` - `default: 8080` порт, прослушиваемый сервером
4. `DEBUG` - `default: false` режим дебага
5. `DB_HOST` - `default: 127.0.0.1` хост базы данных
6. `DB_PORT` - `default: 5432` порт базы данных
7. `DB_USERNAME` - `default: vagabond_dev` пользователь базы данных
8. `DB_PASSWORD` - `default: vagabond_dev` пароль от пользователя для базы данных
9. `HTTP_IO_THREAD_COUNT` - `default: 10` количество I/O потоков на каждого http клиента
10. `TRANSACTIONAL_THREAD_COUNT` - `default: 40` количество потоков, выделяемых на работу с базой данных(должно быть не больше чем `spring.datasource.hikari.maximumPoolSize` - 10)
11. `CRAWLER_THREAD_COUNT` - `default: 1000` количество потоков crawler-а
12. `INDEXER_THREAD_COUNT` - `default: 1500` количество потоков indexer-а

## Makefile

`make build` - сборка jar файла без тестов

`make docker-build` - сборка jar и сборка docker образа с именем `crawler`

`make docker-run` - сборка jar, сборка docker образа и запуск docker контейнера