<template>
	
    <view class="content">
		{{data}}
        <view v-for="(fruit,index) in fruits" :key='index'>
            种类:{{fruit.name}}——颜色{{fruit.color}}
        </view>
    </view>
</template>
<script>
    export default {
        data() {
            return {
				data:'能不能看到',
                fruits: [
                    {
                        name:'苹果',
                        color:'red'
                    },
                    {
                        name:'香蕉',
                        color:'yellow'
                    },
                    {
                        name:'橙子',
                        color:'orange'
                    },
                ]
            }
        }
    }
</script>
