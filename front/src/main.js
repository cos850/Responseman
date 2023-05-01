/** vue Application 생성. 모든 vue 앱은 createApp()를 사용하여 생성한다.
 * 함수 정보 : createApp(rootComponent: Component, rootProps?: object)
 *
 * ※ rootComponent 인수 인라인 전달 (아래 코드 참조)
 * import { createApp } from 'vue'
 *
 * const app = createApp({
 *   // root component options
 * })
 *
 * ※ rootComponent 인수 import 요소 전달 (아래 코드 참조)
 */
import { createApp } from 'vue'
import App from './App.vue'

/**
 * 어플리케이션 인스턴스는 mount() 함수 호출 전까지 아무런 랜더링을 진행하지 않음
 * mount() 함수의 경우 모든 앱 구성요소 등록 후 마지막에 수행되어야 함.
 * ex) 인라인 요소 전달 사용 시
 */
createApp(App).mount('#app')

/**
 * 추가적으로, 여러 어플리케이션 인스턴스를 등록할 수 있음.
 * ex)
 *   const app1 = createApp({
 *       //
 *   })
 *   app1.mount('#container-1')
 *
 *   const app2 = createApp({
 *       //
 *   })
 *   app2.mount('#container-2')
 *
 */