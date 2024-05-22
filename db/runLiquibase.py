#!python3
import os

LIQUIBASE_VERSION = "4.27"

DB_NAME = os.environ.get("DB_NAME", "vagabond_dev")
PORT = os.environ.get("DB_PORT", 5432)
USERNAME = os.environ.get("DB_USERNAME", "vagabond_dev")
PASSWORD = os.environ.get("DB_PASSWORD", "vagabond_dev")

URL = f"jdbc:postgresql://127.0.0.1:{PORT}/{DB_NAME}"
CHANGELOG = "./changelog/root.changelog.xml"
CHANGELOG_DIR = os.path.abspath(os.getcwd()) + "/changelog"

command = f"docker run --rm  --name liquibase --network host --volume {CHANGELOG_DIR}:/liquibase/changelog liquibase/liquibase:{LIQUIBASE_VERSION} --changelog-file={CHANGELOG} --url={URL} --username={USERNAME} --password={PASSWORD} update"

os.system(command)
