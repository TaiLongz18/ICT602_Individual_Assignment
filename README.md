# 🔋 Electricity Bill Estimator (Android App)

This Android application estimates monthly electricity bills based on user input. It calculates total charges using block-based electricity rates, applies a rebate, and stores the results locally for monthly tracking.

## 📱 Features

- Input electricity usage (kWh), month, and rebate percentage
- Calculates total charges using tiered block rates:
  - First 200 kWh: 21.8 sen/kWh
  - Next 100 kWh: 33.4 sen/kWh
  - Next 300 kWh: 51.6 sen/kWh
  - Above 600 kWh: 54.6 sen/kWh
- Applies a rebate percentage (0–5%)
- Stores monthly results in a **local database**
- Displays all months and their final costs in a **ListView**
- Tapping a month shows **detailed breakdown** (month, unit used, total charges, rebate, final cost)
- Clean UI with custom theme, title bar, and icons
- Input validation with helpful messages and error handling
- Includes an **About Page**:
  - Student name
  - Student ID
  - Course code and name
  - Copyright
  - Clickable GitHub URL

## 🧮 Sample Calculation

For 250 kWh and 5% rebate:
- 250 kWh × RM0.218 = RM43.60
- Final Cost: RM43.50 − (5% rebate) = **RM41.42**

## 📂 Technologies Used

- Android Studio
- Java
- SQLite (local database)
- XML (for UI layouts)

## 🧑‍🎓 About the Developer

**Name:** Muhammad Izat Syafik Jainal Abidin  
**Student ID:** 2024554583 
**Course:** ICT602 -  Mobile Technology and Development 
**©** 2025 All rights reserved  
**Website:** [(https://github.com/TaiLongz18/ICT602_Individual_Assignment)](https://github.com/TaiLongz18/ICT602_Individual_Assignment))

## 📹 Demo

Watch a short demo on YouTube: https://youtu.be/k92DDYmreHY

