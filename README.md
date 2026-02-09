# Automation Feasibility Calculator

A simple, offline web tool to quickly evaluate if automating a repetitive business process is worth the effort.  
It calculates **ROI (Return on Investment)** and gives a clear **feasibility rating** (Very High / High / Medium / Low) with practical recommendations.

Built as a fresher project following strict guidelines: pure Java (no frameworks, no Maven/Spring Boot).

## Features
- Takes **6 realistic inputs** including yearly maintenance cost  
- Computes:
  - Yearly manual cost (without automation)  
  - Total automation cost (development + maintenance)  
  - Annual savings  
  - ROI percentage  
  - Feasibility rating & recommendation ("YES – Automate!" or "Not worth it now")  
- Runs **100% offline** – local Java HTTP server  
- Simple browser interface (HTML + CSS + JavaScript)  
- Input validation to prevent crashes  
- No external dependencies

## Demo Screenshots

### Input Form
![Input Form Screenshot](screenshots/input-form.png)  
*(Fill 6 parameters including maintenance cost)*

### Result Output
![Result Screenshot](screenshots/result-high-roi.png)  
*(Shows ROI %, rating, and recommendation)*

*(Add your actual screenshot files in a `screenshots/` folder and update paths)*

## Technologies Used
- **Backend**: Pure Java (JDK 8+) with built-in `com.sun.net.httpserver`  
- **Frontend**: HTML5, CSS3, Vanilla JavaScript  
- **No external libraries** – no Maven, Spring Boot, or third-party JARs

## How to Run

### Prerequisites
- Java JDK 8 or higher installed  
- Any modern browser (Chrome, Edge, Firefox)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/[your-username]/automation-feasibility-calculator.git
   cd automation-feasibility-calculator
