(ns e-commerce-demo.services.image)

;; ------------------------------
;; Image transformation functions
;; ------------------------------

(defn ^:private calc-by-target-width
  "Calculates the potential width and height from an anchor on the target width."
  [t-width o-width o-height]
  (when (not (nil? t-width))
    {:width t-width
     :height (int (/ o-height (/ o-width t-width)))}))

(defn ^:private calc-by-target-height
  "Calculates the potential width and height from an anchor on the target height."
  [t-height o-width o-height]
  (when (not (nil? t-height))
    {:width (int (/ o-width (/ o-height t-height)))
     :height t-height}))

(defn ^:private real-resolution
  "The real resolution is a function of the combined minimum of the target width and height."
  [t-width t-height o-width o-height]
  (let [tmp-1 (calc-by-target-width t-width o-width o-height)
        tmp-2 (calc-by-target-height t-height o-width o-height)
        r-height (min (:height tmp-1) (:height tmp-2))
        r-width (min (:width tmp-1) (:width tmp-2))]
    {:height r-height
     :width r-width}))

(defn parse-image-params
  "Assoc the real dimensions to an image object. It depends on attributes :url
  :width and :height from the input parameter base-image-obj.
  The params height and width specify the intended height and width. The function will
  calculate the minimum resolution based out of any width or height and proportionally
  scale the other dimension.
  Height or width can be nil."
  [base-image-obj t-height t-width]
  (let [{:keys [url width height]} base-image-obj
        target-width (or t-width width)
        target-height (or t-height width)
        real-dimensions (real-resolution target-width target-height
                                         width height)
        real-width (:width real-dimensions)
        real-height (:height real-dimensions)]
    (assoc base-image-obj
           :url (str url real-width "x" real-height ".jpg")
           :width real-width
           :height real-height)))
