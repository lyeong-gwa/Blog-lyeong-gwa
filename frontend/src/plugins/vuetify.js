// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
//import colors from 'vuetify/lib/util/colors';
// Vuetify
import { createVuetify } from 'vuetify'

export default createVuetify(
  // https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
  {
    theme: {
      themes: {
        light: {
          RED: '#fc3d5d',
          BLUE: '#348ed8',
        },
      },
    },
  }
)
