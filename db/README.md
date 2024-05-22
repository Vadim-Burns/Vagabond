# Vagabond Database

Конфигурация базы данных проекта vagabond.

## Структура

1. `changelog/` - папка со всеми changeset-ами, которые описывают структуру базы данных

   a. `changelog/fixtures` - changeset-ы с готовыми данными(необходимо для запуска цикла индексации)

   b. `changelog/infra` - changeset-ы схемы `infra`, используемой для хранения мета данных

   c. `changelog/util` - changeset-ы схемы `util`, используемой для утилит, упрощающих работу с бд(views, функции и тд)

   d. `changelog/vagabond` - changeset-ы схемы `vagabond`, используемой для данных приложения
2. `runLiquibase.py` - скрипт накатки liquibase на базу данных, ключы для подключения можно передать через переменные окружения


## How-To development environment

1. `docker-compose up -d`
2. `python3 runLiquibase.py`