(ns clockwork-ui.core
  (:require  [reagent.core :as reagent]
             [re-frame.core :refer [dispatch dispatch-sync]]
             [clockwork-ui.db]
             [clockwork-ui.events]
             [clockwork-ui.subs]
             [clockwork-ui.navigation :as navigation]
             [clockwork-ui.views :as views]
             ))

(defonce timeslips-updater
  (js/setInterval #(dispatch [:update-clock]) 1000))

(defonce timeslips-saver
  (js/setInterval #(dispatch [:save-timeslips]) 10000))

(defn root-component [env]
  [:div {:class "page"}
   [navigation/main env]
   [views/today env]])

(defn mount-root [setting]
  (let [env (:my-env setting)]
  (reagent/render [root-component env]
            (.getElementById js/document "app"))))

(defn init! [setting]
  (dispatch-sync [:initialise-db])
  (mount-root setting))
