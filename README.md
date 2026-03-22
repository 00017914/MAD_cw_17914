# 🐾 Cat Management Studio (ID: 00017914)

**Cat Management Studio** is a sophisticated Android application designed for feline inventory and adoption management. Developed as a core component of the Mobile Application Development coursework, this application demonstrates a high-level integration of modern Android development practices, including the **MVVM Architecture**, **Jetpack Compose**, and **RESTful API** synchronization.

---

## 🎨 Brand Identity & User Experience (LO4)

The application features a cohesive and professional visual identity designed to be both aesthetically pleasing and highly functional.

* **Launcher Icon:** The "I&B" brand mark provides a distinct, professional presence on the Android home screen.
* **Themed Splash Screen:** To eliminate the "blank start" experience, a custom loading screen featuring a themed cat illustration greets the user.
* **Material 3 Pink Theme:** A custom color palette (using `#D81B60` as Primary) was implemented to create a warm, modern environment. All components, including the status bar, buttons, and surfaces, adhere to this branding.
* **User-Friendly Feedback:** Integrated `SnackbarHost` provides immediate visual confirmation for every network operation (Save, Update, Delete).

---

## 🚀 Key Technical Features

### 1. Robust Data Synchronization
* **Automated Fetching:** Seamlessly retrieves all feline records associated with Student ID `00017914` from the WIUT centralized API.
* **Stock Control:** Features an inline quantity update system, allowing users to modify stock levels without leaving the main list view.
* **State-Aware UI:** Utilizes `mutableStateListOf` to ensure the UI reactively updates as soon as the background data changes.

### 2. Advanced Input Validation
* **Business Logic Constraints:** To ensure data quality, an age limit of **30 years** is strictly enforced.
* **Preventative UX:** The application utilizes numeric-specific soft keyboards to prevent type-mismatch errors before they occur.
* **Compulsory Check:** Validates that Name, Breed, and Age are provided before initiating any server-side communication.

### 3. Graceful Error Handling
* **Network Resilience:** Comprehensive `try-catch` blocks in the ViewModel layer prevent application crashes during connectivity loss.
* **User Notifications:** If a save or update fails, the app communicates the specific reason to the user via a persistent Snackbar.

---

## 🛠 Tech Stack

| Layer | Technology | Implementation Detail |
| :--- | :--- | :--- |
| **UI Framework** | Jetpack Compose | Declarative UI for high performance and responsiveness. |
| **Architecture** | MVVM | Strict separation of concerns for maintainability and testing. |
| **Networking** | Retrofit 2 | Type-safe HTTP client for API interaction. |
| **JSON Mapping** | GSON | Handling complex nested JSON objects and data-type casting. |
| **Asynchrony** | Kotlin Coroutines | Managing background tasks to keep the UI thread smooth. |

---

## 📡 API Specification Integration

The application is fully compliant with the `wiutmadcw.uz` API v1 requirements:

* **POST `/records`**: Encapsulates the UI state into a `Cat` model for record insertion.
* **GET `/records/all`**: Implements a `CatResponse` wrapper to handle the nested `data` array returned by the server.
* **PUT `/records/{id}`**: Utilizes a full-object update strategy to ensure data consistency during partial field changes (like stock updates).

---

## 🛠 Setup & Installation

1.  **Clone the Repository.**
2.  **Gradle Sync:** Open in Android Studio (Flamingo or later recommended).
3.  **Emulator:** Pixel 7 or any device with API 31+ is recommended for the best Material 3 experience.
4.  **Student Registration:** Ensure Student ID `00017914` is registered on the API before first use.

---

### 👨‍💻 Author Information
* **Name:** [Your Name Here]
* **Student ID:** 00017914
* **Module:** Mobile Application Development (W1791)
* **University:** WIUT
