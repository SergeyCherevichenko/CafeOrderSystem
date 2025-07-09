# ☕ CafeOrderSystem — Java-приложение для управления кафе

**CafeOrderSystem** — это Java-программа, предназначенная для автоматизации кафе: регистрация клиентов, оформление заказов, редактирование меню и управление ролями (пользователь / администратор).

Доступны две версии:

- ✅ **v1.0.0** — консольное приложение (один JAR)
- 🌐 **v2.0.0** — графическое приложение на **Swing**, с разделением на **клиент и сервер** через **сокеты**, передача данных через **DTO/JSON**

---

## 📦 Скачать

| Версия | Описание | Ссылки |
|--------|----------|--------|
| `v1.0.0` | Консольное приложение | [📥 CafeOrderSystem-fat.jar](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v1.0.0/CafeOrderSystem-fat.jar) |
| `v2.0.0` | Клиент-сервер + GUI (Swing) | [📥 Client](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderClient-GUI-fat.jar) / [📥 Server](https://github.com/SergeyCherevichenko/CafeOrderSystem/releases/download/v2.0.0/CafeOrderServer-GUI-fat.jar) |

---

## 🚀 Как запустить

### 🔸 Версия `v1.0.0` (консоль)

```bash
java -jar CafeOrderSystem-fat.jar
🔹 Версия v2.0.0 (GUI + клиент-сервер)
Установите JDK 17+

Скачайте оба JAR-файла:

CafeOrderServer-GUI-fat.jar

CafeOrderClient-GUI-fat.jar

Сначала запустите сервер:

bash
Копировать
Редактировать
java -jar CafeOrderServer-GUI-fat.jar
В окне будет отображён локальный IP и порт.

Затем запустите клиент:

bash
Копировать
Редактировать
java -jar CafeOrderClient-GUI-fat.jar
Введите IP и порт сервера — и начните работу.

❗ Клиент не запустится, если не подключился к серверу (проходит проверку соединения заранее)

🔧 Возможности
👤 Регистрация и вход (пользователь / админ)

🍽 Работа с меню (добавление, удаление блюд)

🧾 Заказы: создание, просмотр, удаление

💾 Сохранение / загрузка данных (JSON)

🔌 Работа по сокетам (TCP)

🧩 Передача DTO в JSON-формате между клиентом и сервером

🧪 Тестирование (JUnit 5)

🗂 История версий
Версия	Изменения
✅ v1.0.0	Консольное CLI-приложение
🌐 v2.0.0	Swing GUI, клиент-сервер, сокеты, JSON/DTO
🛠 v3.0.0 (в планах)	Переход на Spring Boot, REST API
☁️ v4.0.0 (в планах)	Docker, PostgreSQL, Swagger UI

📇 Автор
Сергей Черевиченко
📧 cherevichenkoSN@gmail.com
🔗 github.com/SergeyCherevichenko

yaml
Копировать
Редактировать
