const withMT = require("@material-tailwind/react/utils/withMT");
const {COLORS} = require("./src/config/colors.js");
module.exports = withMT({
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors:{
        primary: COLORS.primary,
        primaryDark: COLORS.primaryDark,
        primaryLight: COLORS.primaryLight,
        secondary: COLORS.secondary,
        secondaryDark: COLORS.secondaryDark,
        purple: COLORS.purple,
        white: COLORS.white,
        black: COLORS.black,
        grey: COLORS.grey,
        grey2: COLORS.grey2,
        grey3: COLORS.grey3,

      },
      fontFamily: {
        nunito: ['Nunito', 'ui-sans-serif', 'system-ui'],
      }
    },
  },
  plugins: [],
});