package com.example.dragdrop.adapter

import android.content.ClipDescription
import android.view.DragEvent
import android.view.View

class OnDragAndDropListener {
    class Builder {
        private var _actionDragEnded: (View) -> Unit = {}
        private var _actionDragEntered: (View) -> Unit = {}
        private var _actionDragExited: (View) -> Unit = {}
        private var _actionDragLocation: (View) -> Unit = {}
        private var _actionDragStarted: (View) -> Unit = {}
        private var _actionDrop: (View) -> Unit = {}
        private var _dropListener: (ViewPosition) -> Unit = {}

        fun setActionDragStarted(action: (View) -> Unit): Builder {
            _actionDragStarted = action
            return this
        }

        fun setActionDragEntered(action: (View) -> Unit): Builder {
            _actionDragEntered = action
            return this
        }

        fun setActionDragLocation(action: (View) -> Unit): Builder {
            _actionDragLocation = action
            return this
        }

        fun setActionDragEnded(action: (View) -> Unit): Builder {
            _actionDragEnded = action
            return this
        }

        fun setActionDragExited(action: (View) -> Unit): Builder {
            _actionDragExited = action
            return this
        }

        fun setActionDrop(action: (View) -> Unit): Builder {
            _actionDrop = action
            return this
        }

        fun setDropListener(action: (ViewPosition) -> Unit): Builder {
            _dropListener = action
            return this
        }

        fun build(): View.OnDragListener {
            return View.OnDragListener { view, event ->
                when (event?.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        _actionDragStarted(view)

                        return@OnDragListener event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                    }

                    DragEvent.ACTION_DRAG_ENTERED -> {
                        _actionDragEntered(view)
                    }

                    DragEvent.ACTION_DRAG_ENDED -> {
                        _dropListener(ViewPosition(view, event.x, event.y))
                        _actionDragEnded(view)
                    }

                    DragEvent.ACTION_DRAG_EXITED -> {
                        _actionDragExited(view)
                    }

                    DragEvent.ACTION_DRAG_LOCATION -> {
                        _actionDragLocation(view)
                    }

                    DragEvent.ACTION_DROP -> {
                        _actionDrop(view)
                    }
                }

                false
            }
        }
    }
}