# 🚀 Automation Test Suite

## ✅ Latest Test Execution Summary
- **2 executed**
- **2 passed** (100%)
- **Duration:** 11 seconds
- **Last run:** 7 minutes ago
- **OS:** Windows 11
- **JDK:** OpenJDK 17.0.2+8 (64-Bit Server VM)
- **Framework:** cucumber-jvm 7.22.2

---

## 🧪 Features & Scenarios

### 1️⃣ GitHub API Repositories Test
**Feature File:**  
`src/test/resources/thuanTestResource/01_ApiTest.feature`

**Scenario:** Verify open issues, sorting, and watchers count  
- ✅ Successfully called GitHub API for SeleniumHQ repositories  
- ✅ Retrieved 21 repositories  
- ✅ Total open issues: **1487**  
- ✅ Top 5 repos by updated date:  
  1. selenium | updated_at: 2025-09-05T18:41:28Z  
  2. docker-selenium | updated_at: 2025-09-05T17:53:15Z  
  3. selenium-ide | updated_at: 2025-09-05T09:27:52Z  
  4. seleniumhq.github.io | updated_at: 2025-09-04T14:29:14Z  
  5. selenium-google-code-issue-archive | updated_at: 2025-08-27T12:31:42Z  
- ✅ Repo with most watchers: **selenium (33109 watchers)**  

---

### 2️⃣ OpenWeather City Search Test
**Feature File:**  
`src/test/resources/thuanTestResource/02_OpenWeatherPage.feature`

**Scenario:** Search Los Angeles and verify info  
- ✅ Open OpenWeather website  
- ✅ Search for city `"Los Angeles, US"`  
- ✅ Should see the city name `"Los Angeles, US"`  
- ✅ Should see the current date  
- ✅ Should see the temperature as a number  

---

## ⚙️ Tech Stack
- **Java 17 (OpenJDK)**
- **Maven 3.9.x**
- **Cucumber JVM 7.22.2**
- **JUnit 5.10.2**
- **Selenium Java 4.21.0**

---

## ▶️ Running Tests

### 1. Run with Maven (CLI)
Run all scenarios:
```bash
mvn clean test
```

Run specific feature:
```bash
mvn test -Dcucumber.options="src/test/resources/thuanTestResource/01_ApiTest.feature"
```

Run by tag:
```bash
mvn test -Dcucumber.filter.tags="@apiTest"
```

---

### 2. Run in Eclipse (UI)
1. **Import project** → `File → Import → Existing Maven Project` → chọn folder dự án.  
2. Mở file **Runner class** (ví dụ `TestRunner.java` trong `runners` package).  
3. Right-click → **Run As → JUnit Test**.  
   - Eclipse sẽ chạy Cucumber scenarios giống như chạy JUnit test.  
4. Kết quả hiển thị ở tab **JUnit view** hoặc trong **Console**.  

👉 Ngoài ra bạn có thể:
- Run trực tiếp từ `.feature` file: Right-click → **Run As → Cucumber Feature** (nếu cài plugin Cucumber-Eclipse).  

---

## 📊 Reports
Sau khi chạy, báo cáo sinh ra tại:
- `target/cucumber-report.html`
- `target/cucumber.json`

Có thể tích hợp với plugin **maven-cucumber-reporting** để xuất báo cáo HTML nâng cao.

---

## 📂 Project Structure
```
src
 └── test
     ├── java
     │   ├── stepDefinitions      # Step Definitions (glue code)
     │   ├── pageObjects          # Page Object Model classes
     │   └── runners              # Cucumber Test Runners
     └── resources
         └── thuanTestResource    # Feature files (.feature)
pom.xml                           # Maven configuration
README.md                         # Project documentation
```

---

## 👤 Author
- **ThuanNT** – Automation Test Engineer
