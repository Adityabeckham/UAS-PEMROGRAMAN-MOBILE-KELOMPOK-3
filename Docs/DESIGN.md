---
name: Modern Swiss Utility
colors:
  surface: '#faf9fd'
  surface-dim: '#dbd9dd'
  surface-bright: '#faf9fd'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f4f3f7'
  surface-container: '#efedf1'
  surface-container-high: '#e9e7eb'
  surface-container-highest: '#e3e2e6'
  on-surface: '#1a1b1e'
  on-surface-variant: '#414754'
  inverse-surface: '#2f3033'
  inverse-on-surface: '#f1f0f4'
  outline: '#727785'
  outline-variant: '#c1c6d6'
  surface-tint: '#005bc0'
  primary: '#005bbf'
  on-primary: '#ffffff'
  primary-container: '#1a73e8'
  on-primary-container: '#ffffff'
  inverse-primary: '#adc7ff'
  secondary: '#5b5f64'
  on-secondary: '#ffffff'
  secondary-container: '#dde0e6'
  on-secondary-container: '#5f6368'
  tertiary: '#9e4300'
  on-tertiary: '#ffffff'
  tertiary-container: '#c55500'
  on-tertiary-container: '#0e0200'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#d8e2ff'
  primary-fixed-dim: '#adc7ff'
  on-primary-fixed: '#001a41'
  on-primary-fixed-variant: '#004493'
  secondary-fixed: '#dfe3e8'
  secondary-fixed-dim: '#c3c7cc'
  on-secondary-fixed: '#181c20'
  on-secondary-fixed-variant: '#43474c'
  tertiary-fixed: '#ffdbcb'
  tertiary-fixed-dim: '#ffb691'
  on-tertiary-fixed: '#341100'
  on-tertiary-fixed-variant: '#783100'
  background: '#faf9fd'
  on-background: '#1a1b1e'
  surface-variant: '#e3e2e6'
typography:
  headline-lg:
    fontFamily: Inter
    fontSize: 40px
    fontWeight: '700'
    lineHeight: 48px
    letterSpacing: -0.02em
  headline-lg-mobile:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.02em
  headline-md:
    fontFamily: Inter
    fontSize: 28px
    fontWeight: '600'
    lineHeight: 36px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
    letterSpacing: '0'
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
    letterSpacing: '0'
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
    letterSpacing: '0'
  label-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.01em
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.02em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  unit: 8px
  gutter: 24px
  margin-mobile: 16px
  margin-desktop: 48px
  container-max-width: 1200px
---

## Brand & Style
This design system is anchored in the principles of the International Typographic Style (Swiss Design). It prioritizes clarity, objectivity, and a structural hierarchy that directs the user's attention without unnecessary visual noise. The brand personality is professional, authoritative, and human-centric, designed for high-utility software where information density must be balanced with legibility.

The visual style is **Minimalist** and **Corporate Modern**, eschewing fleeting trends like heavy glassmorphism or neumorphism in favor of a flat, grounded aesthetic. It uses whitespace not as "empty space," but as a functional element to group information and provide visual rest. The result is an interface that feels deliberate, reliable, and expertly crafted.

## Colors
The palette is restricted and functional. **Charcoal Grey** provides high-contrast legibility for primary content, while **Slate Grey** is reserved for secondary information and metadata. **Royal Blue** serves as the primary action color, used sparingly to draw attention to interactive elements and current states.

Surface colors are kept to pure white to maximize the effect of negative space. Status colors (Green and Amber) are desaturated enough to feel professional but remain distinct for immediate recognition of system states.

## Typography
**Inter** is the sole typeface, utilized for its exceptional legibility and neutral, neo-grotesk characteristics. The hierarchy is established through significant weight and size contrasts rather than color shifts. 

Headlines use tight tracking (letter-spacing) and bold weights to anchor the page. Body text is optimized for long-form reading with generous line heights. Labels and captions use medium weights to maintain readability at smaller scales.

## Layout & Spacing
The system employs a strict **8dp grid** for all spatial measurements. Layouts follow a **12-column fluid grid** for desktop, collapsing to 4 columns on mobile. 

Substantial internal padding is a hallmark of this system; components should never feel crowded. Elements within a container should use a minimum of 16px (2 units) of padding, while major section headers should be separated by 48px or 64px to create a sense of importance and clarity. Desktop margins are intentionally generous (48px+) to frame the content and focus the user's eye toward the center.

## Elevation & Depth
Depth is conveyed through **tonal layers** and high-precision **ambient shadows**. 
- **Level 0 (Base):** Pure #FFFFFF background.
- **Level 1 (Cards/Floating elements):** A subtle 1px border (#E8EAED) or an extremely soft shadow (0px 2px 4px rgba(0,0,0,0.05)).
- **Level 2 (Overlays/Modals):** A more defined but still diffused shadow (0px 8px 24px rgba(0,0,0,0.12)).

The design avoids heavy glows or multi-colored shadows. Interactions (e.g., hovering over a button) should feel tactile through slight shifts in tonal value rather than dramatic depth changes.

## Shapes
Shapes are defined by **soft rounded corners (12px)**. This radius is applied consistently to buttons, input fields, and cards to soften the industrial feel of the strict grid. 

Small elements like tags or chips may use a fully rounded "pill" shape to distinguish them from primary structural containers. Icons must maintain a consistent 1.5px or 2px stroke weight with rounded terminals to align with the typography and corner radius.

## Components
- **Buttons:** Primary buttons use a solid Royal Blue fill with white text. Secondary buttons use a subtle Slate Grey outline or a ghost style (no background) to maintain hierarchy. Use 12px corner radius and 12px x 24px padding.
- **Inputs:** Standardized with a 1px Slate Grey border. On focus, the border transitions to Royal Blue with a 2px thickness or a soft blue outer glow (2px spread).
- **Cards:** Cards should have a 1px light grey border or Level 1 elevation. Internal padding should be at least 24px to ensure content breathability.
- **Lists:** Clean, horizontal dividers (1px, #F1F3F4). Use 16px of vertical padding per list item to prevent a cluttered appearance.
- **Chips:** Used for filtering or status. These use a light grey background (#F1F3F4) with 14px Medium weight text.
- **Navigation:** Top-tier navigation uses high-contrast text and a subtle 2px Royal Blue bottom border for the active state, reinforcing the grid-based alignment.