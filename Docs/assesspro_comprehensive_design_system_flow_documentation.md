# Design System: AssessPro - Modern Swiss Utility

## 1. Visual DNA (The Foundation)
**Design Philosophy:** Minimalist, highly functional, Swiss Design principles. Focus on precise grids, deliberate hierarchy, and generous whitespace.

### Brand Identity
- **Logo:** Minimalist abstract geometric logo (stroke art, not filled).
- **Core Theme:** Clean, professional, secure, and low-anxiety.

### Color Palette
- **Background:** `#FFFFFF` (Pure White)
- **Surface:** `#FAF9FD` (Soft Lilac Tint)
- **Primary Text:** `#202124` (Charcoal Grey)
- **Secondary Text:** `#5F6368` (Slate Grey)
- **Primary Accent:** `#1A73E8` (Professional Royal Blue)
- **Success:** `#34A853` (Soft Green)
- **Warning:** `#FBBC04` (Soft Amber)
- **Outline:** `#DBD9DD` (Soft Border)

### Typography (Inter)
- **Display/Headline:** Bold, high contrast, clear hierarchy.
- **Body:** Readable, standard weights for primary content.
- **Labels:** Medium weights for status pills and navigation.

---

## 2. Component Library

### Navigation
- **TopAppBar:** Persistent branding with user profile/notifications.
- **BottomNavBar:** Mobile-first navigation (Beranda, Tes, Orang, Hasil).
- **NavigationDrawer:** Desktop-focused lateral navigation.

### UI Elements
- **Cards:** 12dp rounded corners, subtle drop shadows (2-4dp elevation).
- **Buttons:** Min 48dp height, bold 16sp text, Primary Royal Blue.
- **Input Fields:** Outlined, 1dp border, 12dp corner radius.
- **Status Pills:** Capsule-shaped badges with distinct semantic colors (Yellow for Active, Green for Completed).

---

## 3. End-to-End User Flow

### Flow 1: Portal Masuk (Entry & Login)
- **Goal:** Dual entry point for HR Admins and Candidates.
- **Layout:** Centered content with large negative space.
- **Logic:** Dual choice cards expand into specific login forms or token entry.

### Flow 2: Dashboard HR (Live Monitoring)
- **Goal:** Real-time monitoring of assessment progress.
- **Features:** Quick Stats cards (Registered, Active, Completed) and a Live Candidate List.
- **Interaction:** Status pills update in real-time; FAB for adding new candidates.

### Flow 3: Registrasi Kandidat (Add Candidate)
- **Goal:** Utilitarian form for generating access tokens.
- **Interaction:** Form submission reveals a secure token display area with 'Copy' functionality.

### Flow 4: Sesi Tes (Focus Mode)
- **Goal:** Minimize distraction for candidates.
- **UI:** Persistent timer, progress bar, clear question cards, and large touch-target options.

### Flow 5: Laporan Hasil (Analytics)
- **Goal:** Professional insights into candidate performance.
- **Visuals:** Circular score charts, horizontal category bars, and automated system interpretation.

---

## 4. Technical Implementation (Slicing)
- **Baseline Grid:** 8dp increments.
- **Margins:** 16dp standard horizontal padding.
- **Accessibility:** High contrast ratios for all text; clear focus states for input fields.