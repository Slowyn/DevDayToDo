(ns to-do.utils.helpers)

(defn find-index
  "to find index in collection using the condition given.
  If condition returns true, it returns index of the first element in collection.
  pred - function with condition
  coll - collection"
  [pred coll]
  (count (take-while pred coll)))